package ru.cactus.currency.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.cactus.currency.data.entity.local.Symbols
import ru.cactus.currency.presentation.entity.CardContent
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.repository.DatabaseRepository
import ru.cactus.currency.repository.NetworkRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyUseCases @Inject constructor(
    private val localRepository: DatabaseRepository,
    private val networkRepository: NetworkRepository,
    private val scope: CoroutineScope
) {

    private val _stateUiData: MutableStateFlow<StateUI> = MutableStateFlow(StateUI())
    val stateUiData: StateFlow<StateUI> = _stateUiData

    suspend fun getSymbols() {
        if (stateUiData.value.symbolsMap.isEmpty()) {
            if (localRepository.isSymbolsExists()) {
                with(localRepository.getAllSymbols()) {
                    _stateUiData.update { state ->
                        state.copy(
                            symbolsList = this,
                            symbolsMap = this.associate { it.symbol to it.name }
                        )
                    }
                }
            } else {
                with(networkRepository.getSymbols()) {
                    if (isSuccessful) {
                        body()?.let { data ->
                            _stateUiData.update {
                                it.copy(
                                    symbolsList = data.symbols.map { (symbol, name) ->
                                        Symbols(
                                            symbol = symbol,
                                            name = name
                                        )
                                    }.toList(),
                                    symbolsMap = data.symbols
                                )
                            }
                        }

                        body()?.symbols?.map { Symbols(it.key, it.value) }
                            ?.let { localRepository.insertSymbols(it) }
                    }
                }
            }
        }
    }

    suspend fun getRates(baseCurrency: String) {
        val repo = networkRepository.getCurrenciesRates(baseCurrency)
        if (repo.isSuccessful) {
            _stateUiData.update {
                it.copy(
                    ratesMap = repo.body()?.rates ?: emptyMap(),
                )
            }
        }
    }

    suspend fun updateContent(isHomeContentList: Boolean) {
        val list = mutableListOf(CardContent())
        if (isHomeContentList) {
            list.clear()
            stateUiData.value.symbolsList.forEach { (symbol, name) ->
                list.add(
                    CardContent(
                        symbol = symbol,
                        currencyName = name,
                        rate = stateUiData.value.ratesMap[symbol].toString(),
                        isFavorite = localRepository.isFavoriteSymbol(symbol)
                    )
                )
            }
            _stateUiData.update { it.copy(contentList = list) }
        } else {
            list.clear()
            localRepository.getAllFavorites().forEach { (symbol, name) ->
                list.add(
                    CardContent(
                        symbol = symbol,
                        currencyName = name,
                        rate = stateUiData.value.ratesMap[symbol].toString()
                    )
                )
            }
            _stateUiData.update { it.copy(contentFavoriteList = list) }
        }
    }

    suspend fun addFavorite(symbols: Symbols) {
        localRepository.addToFavorite(
            symbols
        )
        updateContent(true)
    }

    fun setCurrency(symbols: String) {
        scope.launch {
            getRates(symbols)
        }
    }

}
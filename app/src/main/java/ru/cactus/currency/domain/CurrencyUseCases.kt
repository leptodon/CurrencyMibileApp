package ru.cactus.currency.domain

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.cactus.currency.data.entity.local.Favorite
import ru.cactus.currency.data.entity.local.Symbols
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.repository.DatabaseRepository
import ru.cactus.currency.repository.NetworkRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyUseCases @Inject constructor(
    private val localRepository: DatabaseRepository,
    private val networkRepository: NetworkRepository
) {

    private val _stateUiData: MutableStateFlow<StateUI> = MutableStateFlow(StateUI())
    val stateUiData: StateFlow<StateUI> = _stateUiData


    private suspend fun getSymbols(baseCurrency: String) {
        Log.d("TAG", localRepository.isSymbolsExists().toString())

        if (localRepository.isSymbolsExists()) {
            Log.d("TAG", "DATA FROM DATABASE")
            with(localRepository.getAllSymbols()) {
                _stateUiData.update { state ->
                    state.copy(
                        selectedCurrency = baseCurrency,
                        symbolsMap = this.associate { it.symbol to it.name }
                    )
                }
            }
        } else {
            Log.d("TAG", "DATA FROM REMOTE")
            with(networkRepository.getSymbols()) {
                if (isSuccessful) {
                    body()?.let { data ->
                        _stateUiData.update {
                            it.copy(
                                selectedCurrency = baseCurrency,
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

    private suspend fun fetchData(baseCurrency: String) {
        with(networkRepository.getCurrenciesRates(baseCurrency)) {
            if (isSuccessful) {
                body()?.let { data ->
                    _stateUiData.update {
                        it.copy(
                            selectedCurrency = baseCurrency,
                            ratesMap = data.rates
                        )
                    }
                }
            }
        }
    }

    suspend fun setBaseCurrency(base: String) {
        fetchData(base)
        getSymbols(base)
    }

    suspend fun addFavorite(favorite: Favorite) {
        localRepository.insertFavorite(favorite)
    }

    suspend fun getFavorite() = localRepository.getAllFavorites()

}
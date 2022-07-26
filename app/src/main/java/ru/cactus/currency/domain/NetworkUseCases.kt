package ru.cactus.currency.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.cactus.currency.data.entity.CurrenciesRates
import ru.cactus.currency.data.entity.SymbolsList
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.presentation.screens.MainViewModel
import ru.cactus.currency.repository.NetworkRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUseCases @Inject constructor(
    private val repository: NetworkRepository,
    private val scope: CoroutineScope
) {

//    private lateinit var symbols: SymbolsList
//    private lateinit var currenciesRates: CurrenciesRates

    init {
        scope.launch {
            fetchData()
            getSymbols()
        }
    }

    private var curCurrency: String = "USD"
        set(value) {
            field = value
            scope.launch {
                fetchData()
                getSymbols()
            }
        }

    private val _stateUiData: MutableStateFlow<StateUI> = MutableStateFlow(StateUI())
    val stateUiData: StateFlow<StateUI> = _stateUiData

    private suspend fun getSymbols() {
        with(repository) {
            if (getSymbols().isSuccessful) {
                getSymbols().body()?.let { data ->
//                    symbols = data

                    _stateUiData.update {
                        it.copy(
                            selectedCurrency = curCurrency,
                            symbolsMap = data.symbols
                        )
                    }
                }

            }
        }
    }

    private suspend fun fetchData() {
        with(repository.getCurrenciesRates(curCurrency)) {
            if (isSuccessful) {
                body()?.let { data ->
//                    currenciesRates = data
                    _stateUiData.update {
                        it.copy(
                            selectedCurrency = curCurrency,
                            ratesMap = data.rates
                        )
                    }
                }

            }
        }
    }

    fun setCurrency(base: String) {
        curCurrency = base
    }

}
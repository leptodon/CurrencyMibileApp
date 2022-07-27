package ru.cactus.currency.domain

import android.util.Log
import kotlinx.coroutines.flow.*
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
                Log.d("CurrencyUseCases", "getSymbols is complete")
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
                Log.d("CurrencyUseCases", "fetchData is complete")
            }
        }
    }

    suspend fun setBaseCurrency(base: String) {
        fetchData(base)
        getSymbols(base)
    }

}
package ru.cactus.currency.presentation.screens

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.cactus.currency.data.entity.local.Favorite
import ru.cactus.currency.domain.CurrencyUseCases
import ru.cactus.currency.presentation.entity.StateUI
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyUseCases: CurrencyUseCases,
    application: Application
) : AndroidViewModel(application) {

    private val _homeScreenState: MutableStateFlow<StateUI> = MutableStateFlow(StateUI())
    val homeScreenState: StateFlow<StateUI> = _homeScreenState

    private val _favoriteCurrencyList: MutableStateFlow<List<Favorite>> =
        MutableStateFlow(emptyList())
    val favoriteCurrencyList: StateFlow<List<Favorite>> = _favoriteCurrencyList


    init {
        viewModelScope.launch(Dispatchers.IO) {
            currencyUseCases.setBaseCurrency("USD")
            currencyUseCases.stateUiData.collect { state ->
                _homeScreenState.update {
                    it.copy(
                        selectedCurrency = state.selectedCurrency,
                        symbolsMap = state.symbolsMap,
                        ratesMap = state.ratesMap
                    )
                }
            }
        }
    }

    fun setBaseCurrency(base: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currencyUseCases.setBaseCurrency(base)
            currencyUseCases.stateUiData.collect { state ->
                _homeScreenState.update {
                    it.copy(
                        selectedCurrency = state.selectedCurrency,
                        symbolsMap = state.symbolsMap,
                        ratesMap = state.ratesMap
                    )
                }
            }
        }
    }

    fun addToFavorite(symbol: String, currency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currencyUseCases.addFavorite(
                Favorite(
                    symbol = symbol,
                    name = currency
                )
            )
        }
    }

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteCurrencyList.emit(
                currencyUseCases.getFavorite()
            )
        }
    }

    fun filterListByAlphabet(){

    }

    fun filterListByRates(){

    }

}
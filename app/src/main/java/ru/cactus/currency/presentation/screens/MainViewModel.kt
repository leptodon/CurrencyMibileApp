package ru.cactus.currency.presentation.screens

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.cactus.currency.domain.CurrencyUseCases
import ru.cactus.currency.presentation.entity.StateUI
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyUseCases: CurrencyUseCases,
    application: Application
) : AndroidViewModel(application) {

    private val _viewModelState: MutableStateFlow<StateUI> = MutableStateFlow(StateUI())
    val viewModelState: StateFlow<StateUI> = _viewModelState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            currencyUseCases.setBaseCurrency("USD")
            currencyUseCases.stateUiData.collect { state ->
                _viewModelState.update {
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
                _viewModelState.update {
                    it.copy(
                        selectedCurrency = state.selectedCurrency,
                        symbolsMap = state.symbolsMap,
                        ratesMap = state.ratesMap
                    )
                }
            }
        }
    }

//    fun addToFavorite(symbol: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            dbRepository.insertFavorite(
//                Favorite(
//                    symbol = symbol,
////                    name = symbols.symbols[symbol] ?: ""
//                )
//            )
//        }
//    }
//
//    fun getFavorites() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _favoritesList.emit(dbRepository.getAllFavorites())
//        }
//    }

}
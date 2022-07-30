package ru.cactus.currency.presentation.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.cactus.currency.data.entity.local.Symbols
import ru.cactus.currency.domain.CurrencyUseCases
import ru.cactus.currency.presentation.entity.StateUI
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyUseCases: CurrencyUseCases,
    application: Application,
    private val scope: CoroutineScope
) : AndroidViewModel(application) {

    val homeScreenState: StateFlow<StateUI> = currencyUseCases.stateUiData

    fun getSymbols() = scope.launch {
        currencyUseCases.getSymbols()
        currencyUseCases.getRates("AED")
    }

    fun changeFavoriteStatus(symbol: String, currency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currencyUseCases.changeFavorite(
                Symbols(
                    symbol = symbol,
                    name = currency
                )
            )
        }
    }

    fun homeContent() {
        scope.launch {
            currencyUseCases.updateContent(true)
        }
    }

    fun favoriteContent() {
        scope.launch {
            currencyUseCases.updateContent(false)
        }
    }

    fun setBaseCurrency(symbols: String) {
        currencyUseCases.setCurrency(symbols)
    }

    fun filterByRate(text:String){
        if (text == "Filter by rate") {
            currencyUseCases.filter(false)
        } else if ( text == "Filter by alphabet"){
            currencyUseCases.filter(true)
        }
    }

}
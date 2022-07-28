package ru.cactus.currency.presentation.screens

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.cactus.currency.data.entity.local.Symbols
import ru.cactus.currency.domain.CurrencyUseCases
import ru.cactus.currency.presentation.entity.CardContent
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
        currencyUseCases.getRates("USD")
    }

    fun addToFavorite(symbol: String, currency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currencyUseCases.addFavorite(
                Symbols(
                    symbol = symbol,
                    name = currency,
                    isFavorite = true
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

}
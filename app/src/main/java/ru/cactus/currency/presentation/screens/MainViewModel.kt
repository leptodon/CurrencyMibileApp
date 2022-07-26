package ru.cactus.currency.presentation.screens

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.cactus.currency.data.entity.CurrenciesRates
import ru.cactus.currency.data.entity.Favorite
import ru.cactus.currency.data.entity.SymbolsList
import ru.cactus.currency.data.db.DatabaseRepositoryImpl
import ru.cactus.currency.data.network.NetworkRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkRepository: NetworkRepositoryImpl,
    private val dbRepository: DatabaseRepositoryImpl,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableSharedFlow<Map<String, String>> = MutableSharedFlow()
    private val _ratesMap: MutableSharedFlow<Map<String, String>> = MutableSharedFlow()
    private val _favoritesList: MutableSharedFlow<List<Favorite>> = MutableSharedFlow()



    val favoritesList: Flow<List<Favorite>> = _favoritesList
    get() {
        getFavorites()
        fetchData()
        return field
    }
    val ratesMap: Flow<Map<String, String>> = _ratesMap
    val response: Flow<Map<String, String>> = _response
    var curCurrency:String = "USD"

    private lateinit var symbols: SymbolsList
    private lateinit var currenciesRates: CurrenciesRates

    init {
        viewModelScope.launch(Dispatchers.IO) {
            networkRepository.getSymbols().collect { result ->
                result.data?.let { symbols = it }
                _response.emit(symbols.symbols)
            }

            networkRepository.getCurrenciesRates("USD").collect { result ->
                result.data?.let { currenciesRates = it }
                _ratesMap.emit(currenciesRates.rates)
            }

            _favoritesList.emit(dbRepository.getAllFavorites())
        }
    }

//    fun fetchData(baseCurrency: String = "USD") {
    fun fetchData() {
        viewModelScope.launch {
            networkRepository.getCurrenciesRates(curCurrency).collect { result ->

                result.data?.let { rates ->
                    currenciesRates =
                        rates.copy(rates = rates.rates.filter { it.key != curCurrency })
                }
                _ratesMap.emit(currenciesRates.rates)
            }
        }
    }

    fun addToFavorite(symbol: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.insertFavorite(
                Favorite(
                    symbol = symbol,
                    name = symbols.symbols[symbol] ?: ""
                )
            )
        }
    }

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            _favoritesList.emit(dbRepository.getAllFavorites())
        }
    }

    fun setCurrentCurrency(currency: String) {
        curCurrency = currency
    }
}
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
import ru.cactus.currency.domain.NetworkUseCases
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.repository.NetworkRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DatabaseRepositoryImpl,
    private val networkUseCases: NetworkUseCases,
    application: Application
) : AndroidViewModel(application) {

/*
    private val _response: MutableSharedFlow<Map<String, String>> = MutableSharedFlow()
    private val _ratesMap: MutableSharedFlow<Map<String, String>> = MutableSharedFlow()
    private val _favoritesList: MutableSharedFlow<List<Favorite>> = MutableSharedFlow()
*/

    private val _viewModelState: MutableStateFlow<StateUI> = MutableStateFlow(StateUI())
    val viewModelState: StateFlow<StateUI>
        get() = _viewModelState

    fun setBaseCurrency(base: String) {
        networkUseCases.setCurrency(base)
        viewModelScope.launch {
//            _viewModelState.update { it.copy(selectedCurrency = base) }
            networkUseCases.stateUiData.collect{
//                _viewModelState.update { it }
                _viewModelState.emit(it)
            }
        }
    }


/*    val favoritesList: Flow<List<Favorite>> = _favoritesList
        get() {
            getFavorites()
            fetchData()
            return field
        }

    val ratesMap: Flow<Map<String, String>> = _ratesMap
    val response: Flow<Map<String, String>> = _response*/

    var curCurrency: String = "USD"

    private lateinit var symbols: SymbolsList
    private lateinit var currenciesRates: CurrenciesRates

//    init {
//        getSymbols()
//        getFavorites()
//        fetchData()
//    }

//    private fun getSymbols() {
//        viewModelScope.launch(Dispatchers.IO) {
//            networkRepository.getSymbols().collect { result ->
//                if (result.isSuccessful) {
//                    result.body()?.let {
//                        symbols = it
////                        _response.emit(it.symbols)
//                    }
//                }
//            }
//        }
//    }
//
//    fun fetchData() {
//        viewModelScope.launch {
//            networkRepository.getCurrenciesRates(curCurrency).collect { result ->
//                if (result.isSuccessful) {
//                    result.body()?.let { value ->
//                        currenciesRates =
//                            value.copy(rates = value.rates.filter { it.key != curCurrency })
////                        _ratesMap.emit(currenciesRates.rates)
//                    }
//                }
//            }
//        }
//    }

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
//            _favoritesList.emit(dbRepository.getAllFavorites())
        }
    }

    fun setCurrentCurrency(currency: String) {
        curCurrency = currency
    }
}
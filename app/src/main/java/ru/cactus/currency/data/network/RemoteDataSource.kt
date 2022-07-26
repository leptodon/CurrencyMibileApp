package ru.cactus.currency.data.network

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val networkService: NetworkService) {
/*    suspend fun getSymbols() = networkService.getSymbols()
    suspend fun getCurrenciesRates(base:String) =
        networkService.getCurrenciesRates(base = base)*/
}
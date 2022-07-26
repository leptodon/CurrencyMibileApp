package ru.cactus.currency.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.cactus.currency.data.entity.CurrenciesRates
import ru.cactus.currency.data.entity.SymbolsList

interface NetworkService {
    @GET("symbols")
    suspend fun getSymbols(): Response<SymbolsList>

    @GET("latest")
    suspend fun getCurrenciesRates(
        @Query("base") base: String = "USD"
    ): Response<CurrenciesRates>
}
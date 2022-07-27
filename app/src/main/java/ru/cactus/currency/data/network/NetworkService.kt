package ru.cactus.currency.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.cactus.currency.data.entity.network.CurrenciesRatesResponse
import ru.cactus.currency.data.entity.network.SymbolsResponse

interface NetworkService {
    @GET("symbols")
    suspend fun getSymbols(): Response<SymbolsResponse>

    @GET("latest")
    suspend fun getCurrenciesRates(
        @Query("base") base: String = "USD"
    ): Response<CurrenciesRatesResponse>
}
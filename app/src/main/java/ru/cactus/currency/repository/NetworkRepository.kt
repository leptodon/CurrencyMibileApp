package ru.cactus.currency.repository

import retrofit2.Response
import ru.cactus.currency.data.entity.CurrenciesRatesResponse
import ru.cactus.currency.data.entity.SymbolsResponse

interface NetworkRepository {
    suspend fun getSymbols(): Response<SymbolsResponse>
    suspend fun getCurrenciesRates(base: String): Response<CurrenciesRatesResponse>
}
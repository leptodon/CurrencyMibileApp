package ru.cactus.currency.domain.repository

import retrofit2.Response
import ru.cactus.currency.data.entity.network.CurrenciesRatesResponse
import ru.cactus.currency.data.entity.network.SymbolsResponse

interface NetworkRepository {
    suspend fun getSymbols(): Response<SymbolsResponse>
    suspend fun getCurrenciesRates(base: String): Response<CurrenciesRatesResponse>
}
package ru.cactus.currency.repository

import retrofit2.Response
import ru.cactus.currency.data.entity.CurrenciesRates
import ru.cactus.currency.data.entity.SymbolsList

interface NetworkRepository {
    suspend fun getSymbols(): Response<SymbolsList>
    suspend fun getCurrenciesRates(base: String): Response<CurrenciesRates>
}
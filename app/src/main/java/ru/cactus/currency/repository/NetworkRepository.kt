package ru.cactus.currency.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import ru.cactus.currency.data.entity.CurrenciesRates
import ru.cactus.currency.data.entity.SymbolsList

interface NetworkRepository {
    suspend fun getSymbols(): Flow<Response<SymbolsList>>
    suspend fun getCurrenciesRates(base: String): Flow<Response<CurrenciesRates>>
}
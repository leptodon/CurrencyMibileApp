package ru.cactus.currency.data.network

import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import ru.cactus.currency.data.entity.CurrenciesRatesResponse
import ru.cactus.currency.data.entity.SymbolsResponse
import ru.cactus.currency.repository.NetworkRepository
import javax.inject.Inject

@ActivityRetainedScoped
class NetworkRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : NetworkRepository {

    override suspend fun getSymbols(): Response<SymbolsResponse> = networkService.getSymbols()

    override suspend fun getCurrenciesRates(base: String): Response<CurrenciesRatesResponse> =
        networkService.getCurrenciesRates(base = base)
}
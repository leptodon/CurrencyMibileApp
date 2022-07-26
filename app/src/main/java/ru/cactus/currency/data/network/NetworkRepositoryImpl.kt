package ru.cactus.currency.data.network

import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import ru.cactus.currency.data.entity.CurrenciesRates
import ru.cactus.currency.data.entity.SymbolsList
import ru.cactus.currency.repository.NetworkRepository
import javax.inject.Inject

@ActivityRetainedScoped
class NetworkRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : NetworkRepository {

    override suspend fun getSymbols(): Response<SymbolsList> = networkService.getSymbols()

    override suspend fun getCurrenciesRates(base: String): Response<CurrenciesRates> =
        networkService.getCurrenciesRates(base = base)
}

/*: BaseApiResponse() {
    suspend fun getSymbols(): Flow<NetworkResult<SymbolsList>> {
        return flow<NetworkResult<SymbolsList>> {
            emit(safeApiCall { remoteDataSource.getSymbols() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCurrenciesRates(
        base: String
    ): Flow<NetworkResult<CurrenciesRates>> {
        return flow<NetworkResult<CurrenciesRates>> {
            emit(safeApiCall {
                remoteDataSource.getCurrenciesRates(base = base)
            })
        }.flowOn(Dispatchers.IO)
    }
}*/
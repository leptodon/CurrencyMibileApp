package ru.cactus.currency.data.network

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import ru.cactus.currency.data.network.BaseApiResponse
import ru.cactus.currency.data.network.RemoteDataSource
import ru.cactus.currency.data.entity.CurrenciesRates
import ru.cactus.currency.data.entity.SymbolsList
import ru.cactus.currency.repository.NetworkRepository
import ru.cactus.currency.utils.NetworkResult
import javax.inject.Inject

@ActivityRetainedScoped
class NetworkRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : NetworkRepository {
    override suspend fun getSymbols(): Flow<Response<SymbolsList>> = flow {
        emit(remoteDataSource.getSymbols())
    }.flowOn(Dispatchers.IO)

    override suspend fun getCurrenciesRates(base: String): Flow<Response<CurrenciesRates>> = flow {
        emit(remoteDataSource.getCurrenciesRates(base = base))
    }.flowOn(Dispatchers.IO)

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
package ru.cactus.currency.domain

import ru.cactus.currency.data.network.NetworkRepositoryImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUseCases @Inject constructor(private val repository: NetworkRepositoryImpl) {



}
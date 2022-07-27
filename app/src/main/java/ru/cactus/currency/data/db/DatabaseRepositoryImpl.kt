package ru.cactus.currency.data.db

import ru.cactus.currency.data.entity.local.Favorite
import ru.cactus.currency.data.entity.local.Symbols
import ru.cactus.currency.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val favoriteSymbolsDao: FavoriteSymbolsDao,
    private val allSymbolsDao: AllSymbolsDao
) : DatabaseRepository {

    override suspend fun getAllFavorites(): List<Favorite> = favoriteSymbolsDao.getAllFavorites()

    override suspend fun insertFavorite(favorite: Favorite) {
        favoriteSymbolsDao.insertFavorite(favorite)
    }

    override suspend fun getAllSymbols(): List<Symbols> = allSymbolsDao.getAllSymbols()

    override suspend fun insertSymbols(symbols: List<Symbols>) {
        allSymbolsDao.insertSymbols(symbols)
    }
}
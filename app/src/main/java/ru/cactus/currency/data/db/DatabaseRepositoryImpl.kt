package ru.cactus.currency.data.db

import ru.cactus.currency.data.entity.local.Symbols
import ru.cactus.currency.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val allSymbolsDao: AllSymbolsDao
) : DatabaseRepository {

    override suspend fun getAllSymbols(): List<Symbols> = allSymbolsDao
        .getAllSymbols()

    override suspend fun insertSymbols(symbols: List<Symbols>) {
        allSymbolsDao.insertSymbols(symbols)
    }

    override suspend fun isSymbolsExists(): Boolean = allSymbolsDao
        .isSymbolsExists()

    override suspend fun addToFavorite(symbols: Symbols) = allSymbolsDao.addToFavorite(symbols)
    override suspend fun getAllFavorites(): List<Symbols> = allSymbolsDao
        .getAllFavorites()

    override suspend fun isFavoriteSymbol(symbolName: String): Boolean = allSymbolsDao
        .isFavoriteSymbol(symbolName)
}
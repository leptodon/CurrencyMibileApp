package ru.cactus.currency.repository

import ru.cactus.currency.data.entity.local.Favorite
import ru.cactus.currency.data.entity.local.Symbols

interface DatabaseRepository {
    suspend fun getAllFavorites(): List<Favorite>
    suspend fun insertFavorite(favorite: Favorite)
    suspend fun getAllSymbols(): List<Symbols>
    suspend fun insertSymbols(symbols: List<Symbols>)
    suspend fun isSymbolsExists():Boolean
}
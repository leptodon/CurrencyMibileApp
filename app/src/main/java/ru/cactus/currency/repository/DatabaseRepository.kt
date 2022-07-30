package ru.cactus.currency.repository

import ru.cactus.currency.data.entity.local.Symbols

interface DatabaseRepository {
    suspend fun getAllSymbols(): List<Symbols>
    suspend fun insertSymbols(symbols: List<Symbols>)
    suspend fun isSymbolsExists():Boolean

    suspend fun changeFavoriteStatus(symbols: Symbols)
    suspend fun getAllFavorites(): List<Symbols>
    suspend fun isFavoriteSymbol(symbolName:String):Boolean
}
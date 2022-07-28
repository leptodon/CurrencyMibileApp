package ru.cactus.currency.data.db

import androidx.room.*
import ru.cactus.currency.data.entity.local.Symbols

@Dao
interface AllSymbolsDao {
    @Query("SELECT * FROM symbols")
    fun getAllSymbols(): List<Symbols>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSymbols(symbols: List<Symbols>)

    @Query("SELECT EXISTS(SELECT * FROM symbols)")
    fun isSymbolsExists(): Boolean

    @Update
    fun addToFavorite(symbols: Symbols)

    @Query("SELECT isFavorite FROM Symbols WHERE symbol=:name")
    fun isFavoriteSymbol(name: String):Boolean

    @Query("SELECT * FROM Symbols WHERE isFavorite=1")
    fun getAllFavorites(): List<Symbols>

}
package ru.cactus.currency.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.cactus.currency.data.entity.local.Symbols

@Dao
interface AllSymbolsDao {
    @Query("SELECT * FROM favorite")
    fun getAllSymbols(): List<Symbols>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSymbols(symbols: List<Symbols>)
}
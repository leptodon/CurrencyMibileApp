package ru.cactus.currency.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.cactus.currency.data.entity.local.Symbols

@Database(entities = [Symbols::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun allSymbolsDao(): AllSymbolsDao
}
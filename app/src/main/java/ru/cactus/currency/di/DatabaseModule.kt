package ru.cactus.currency.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.cactus.currency.data.db.AllSymbolsDao
import ru.cactus.currency.data.db.AppDatabase
import ru.cactus.currency.data.db.DatabaseRepositoryImpl
import ru.cactus.currency.domain.repository.DatabaseRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideAllSymbolsDao(appDatabase: AppDatabase): AllSymbolsDao {
        return appDatabase.allSymbolsDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Currency"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocalRepository(
        allSymbolsDao: AllSymbolsDao
    ): DatabaseRepository =
        DatabaseRepositoryImpl(allSymbolsDao)
}
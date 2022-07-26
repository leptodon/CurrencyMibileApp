package ru.cactus.currency.data.db

import ru.cactus.currency.data.db.FavoriteDao
import ru.cactus.currency.data.entity.Favorite
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) {
    fun getAllFavorites(): List<Favorite> = favoriteDao.getAllFavorites()

    fun insertFavorite(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite)
    }
}
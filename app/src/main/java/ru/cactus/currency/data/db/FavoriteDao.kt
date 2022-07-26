package ru.cactus.currency.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.cactus.currency.data.entity.Favorite

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): List<Favorite>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavorite(favorite: Favorite)
}
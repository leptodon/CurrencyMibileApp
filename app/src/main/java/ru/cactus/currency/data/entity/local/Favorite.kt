package ru.cactus.currency.data.entity.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey
    val symbol: String,
    val name: String
)

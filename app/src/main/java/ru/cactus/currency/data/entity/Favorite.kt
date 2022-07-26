package ru.cactus.currency.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey
    val symbol: String,
    val name: String
)

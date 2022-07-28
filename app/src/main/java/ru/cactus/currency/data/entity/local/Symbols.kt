package ru.cactus.currency.data.entity.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Symbols(
    @PrimaryKey
    val symbol: String,
    val name: String,
    val isFavorite: Boolean = false
)

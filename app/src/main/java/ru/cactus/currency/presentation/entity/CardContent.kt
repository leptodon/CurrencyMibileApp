package ru.cactus.currency.presentation.entity

data class CardContent(
    val symbol: String = "",
    val currencyName: String = "",
    val rate: String = "",
    val currencySymbolImg: String = "",
    val isFavorite: Boolean = false
)

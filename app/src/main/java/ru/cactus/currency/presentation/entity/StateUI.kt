package ru.cactus.currency.presentation.entity

import ru.cactus.currency.data.entity.local.Symbols

data class StateUI(
    val selectedCurrency: String = "AED",
    val contentList: List<CardContent> = emptyList(),
    val contentFavoriteList: List<CardContent> = emptyList(),
    val symbolsList: List<Symbols> = emptyList(),
    val symbolsMap: Map<String, String> = emptyMap(),
    val ratesMap: Map<String, String> = mapOf("AED" to "0.000")
)
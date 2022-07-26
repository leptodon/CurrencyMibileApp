package ru.cactus.currency.presentation.entity

data class StateUI(
    val selectedCurrency: String = "USD",
    val symbolsMap: Map<String, String> = emptyMap(),
    val ratesMap: Map<String, String> = emptyMap()
)

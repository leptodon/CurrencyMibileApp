package ru.cactus.currency.presentation.entity

data class StateUI(
    val selectedCurrency: String = "USD",
    val symbolsMap: Map<String, String> = mapOf("USD" to "United States Dollar"),
    val ratesMap: Map<String, String> = mapOf("USD" to "0.000")
)
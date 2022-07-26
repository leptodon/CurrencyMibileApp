package ru.cactus.currency.data.entity

data class CurrenciesRates(
    val base: String,
    val date: String,
    val rates: Map<String, String>,
    val success: Boolean,
    val timestamp: Int
)
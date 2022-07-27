package ru.cactus.currency.data.entity.network

data class CurrenciesRatesResponse(
    val base: String,
    val date: String,
    val rates: Map<String, String>,
    val success: Boolean,
    val timestamp: Int
)
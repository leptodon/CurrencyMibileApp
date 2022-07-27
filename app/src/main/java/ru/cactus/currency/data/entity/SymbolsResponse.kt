package ru.cactus.currency.data.entity

data class SymbolsResponse(
    val success: Boolean,
    val symbols: Map<String,String>
)
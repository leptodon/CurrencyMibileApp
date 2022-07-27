package ru.cactus.currency.data.entity.network

data class SymbolsResponse(
    val success: Boolean,
    val symbols: Map<String,String>
)
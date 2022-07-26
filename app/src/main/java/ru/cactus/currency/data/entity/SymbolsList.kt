package ru.cactus.currency.data.entity

data class SymbolsList(
    val success: Boolean,
    val symbols: Map<String,String>
)
package ru.cactus.currency.presentation.entity

import java.util.*

data class StateUI(
    val selectedCurrency: Currency,
    val symbolsMap: Map<String, String>,
    val ratesMap: Map<String, String>
)

package ru.cactus.currency.presentation.screens.commonComponents

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
internal fun CurrencyList(
    symbols: Map<String, String>,
    rates: Map<String, String>,
    onItemClick: (String) -> Unit
){
    if (rates.isNotEmpty()) {
        LazyColumn {
            symbols.forEach { (symbol, name) ->
                item {
                    rates[symbol]?.let {
                        CurrencyCard(
                            currency = name,
                            symbol = symbol,
                            rate = it,
                            onItemClick = onItemClick
                        )
                    }
                }
            }
        }
    }
}
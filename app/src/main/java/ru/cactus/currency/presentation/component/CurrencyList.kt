package ru.cactus.currency.presentation.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
internal fun CurrencyList(symbols: Map<String, String>, rates: Map<String, String>) {
/*    if (rates.isNotEmpty()) {
        LazyColumn {
            symbols.forEach { (symbol, name) ->
                item {
                    rates[symbol]?.let {
                        CurrencyCard(
                            currency = name,
                            symbol = symbol,
                            rate = it
                        )
                    }
                }
            }
        }
    }*/
}
package ru.cactus.currency.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import ru.cactus.currency.data.entity.Favorite
import ru.cactus.currency.presentation.component.CurrencyCard

@Composable
fun FavoriteScreen(
    symbols: List<Favorite>,
    rates: Map<String, String>
) {
    if (rates.isNotEmpty()) {
        LazyColumn {
            symbols.forEach { item ->
                item {
                    rates[item.symbol]?.let {
                        CurrencyCard(
                            currency = item.name,
                            symbol = item.symbol,
                            rate = it
                        )
                    }
                }
            }
        }
    }
}
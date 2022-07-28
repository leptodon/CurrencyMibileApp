package ru.cactus.currency.presentation.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ru.cactus.currency.presentation.entity.CardContent
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.presentation.screens.MainViewModel
import ru.cactus.currency.presentation.screens.components.CurrencyCard

@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {
    val uiState: StateUI by viewModel.homeScreenState.collectAsState()

    viewModel.homeContent()
    if (uiState.contentList.isNotEmpty()) {
        LazyColumn {
            uiState.contentList.forEach { item ->
                item {
                    CurrencyCard(
                        currency = item.currencyName,
                        symbol = item.symbol,
                        rate = item.rate,
                        isFavorite = item.isFavorite
                    ) { symbol, currency -> viewModel.addToFavorite(symbol, currency) }
                }
            }
        }
    }
}
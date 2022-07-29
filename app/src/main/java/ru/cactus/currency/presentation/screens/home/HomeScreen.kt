package ru.cactus.currency.presentation.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.presentation.screens.MainViewModel
import ru.cactus.currency.presentation.screens.components.CurrencyCard

@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState: StateUI by viewModel.homeScreenState.collectAsState()

    LaunchedEffect(key1 = true){
        viewModel.homeContent()
    }
    if (uiState.contentList.isNotEmpty()) {
        LazyColumn {
            uiState.contentList.forEach { item ->
                item {
                    CurrencyCard(
                        currency = item.currencyName,
                        symbol = item.symbol,
                        rate = uiState.ratesMap[item.symbol]?:"0.0",
                        isFavorite = item.isFavorite,
                        img = item.currencySymbolImg
                    ) { symbol, currency -> viewModel.addToFavorite(symbol, currency) }
                }
            }
        }
    }
}
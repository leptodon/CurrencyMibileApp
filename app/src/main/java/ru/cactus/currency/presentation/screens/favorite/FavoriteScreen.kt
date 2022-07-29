package ru.cactus.currency.presentation.screens.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.presentation.screens.MainViewModel
import ru.cactus.currency.presentation.screens.components.CurrencyCard

@Composable
fun FavoriteScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState: StateUI by viewModel.homeScreenState.collectAsState()

    if (uiState.contentFavoriteList.isNotEmpty()) {
        LazyColumn {
            uiState.contentFavoriteList.forEach { item ->
                item {
                        CurrencyCard(
                            currency = item.currencyName,
                            symbol = item.symbol,
                            rate = uiState.ratesMap[item.symbol]?:"0.0",
                            img = item.currencySymbolImg
                        )
                }
            }
        }
    }
}
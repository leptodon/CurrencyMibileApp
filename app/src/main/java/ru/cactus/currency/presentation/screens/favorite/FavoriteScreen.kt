package ru.cactus.currency.presentation.screens.favorite

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
fun FavoriteScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState: StateUI by viewModel.homeScreenState.collectAsState()

    viewModel.favoriteContent()
    if (uiState.contentFavoriteList.isNotEmpty()) {
        LazyColumn {
            uiState.contentFavoriteList.forEach { item ->
                item {
                    uiState.ratesMap[item.symbol]?.let {
                        CurrencyCard(
                            currency = item.currencyName,
                            symbol = item.symbol,
                            rate = it
                        )
                    }
                }
            }
        }
    }
}
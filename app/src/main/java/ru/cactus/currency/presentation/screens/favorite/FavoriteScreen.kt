package ru.cactus.currency.presentation.screens.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.presentation.screens.MainViewModel
import ru.cactus.currency.presentation.screens.commonComponents.CurrencyCard

@Composable
fun FavoriteScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState: StateUI by viewModel.viewModelState.collectAsState()

    if (uiState.symbolsMap.isNotEmpty()) {
        LazyColumn {
            uiState.symbolsMap.forEach { item ->
                item {
                    uiState.ratesMap[item.key]?.let {
                        CurrencyCard(
                            currency = item.value,
                            symbol = item.key,
                            rate = it
                        )
                    }
                }
            }
        }
    }
}
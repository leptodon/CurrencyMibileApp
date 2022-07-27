package ru.cactus.currency.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.presentation.screens.MainViewModel

@Composable
internal fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel(),) {
    val uiState: StateUI by viewModel.viewModelState.collectAsState()

        if (uiState.symbolsMap.isNotEmpty()) {
            CurrencyList(symbols = uiState.symbolsMap, rates = uiState.ratesMap) {}
        }
}
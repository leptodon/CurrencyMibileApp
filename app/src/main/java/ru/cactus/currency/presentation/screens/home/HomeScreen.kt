package ru.cactus.currency.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import ru.cactus.currency.presentation.screens.commonComponents.AppBar
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.presentation.screens.commonComponents.CurrencyList
import ru.cactus.currency.presentation.screens.MainViewModel
import ru.cactus.currency.presentation.screens.commonComponents.BottomNavigationBar

@Composable
internal fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState: StateUI by viewModel.viewModelState.collectAsState()
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            AppBar(symbols = uiState.symbolsMap)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ){
        CurrencyList(symbols = uiState.symbolsMap, rates = uiState.ratesMap){}
    }
}
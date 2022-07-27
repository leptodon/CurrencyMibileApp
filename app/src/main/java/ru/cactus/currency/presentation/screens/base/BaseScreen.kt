package ru.cactus.currency.presentation.screens.base

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.presentation.navigation.NavGraph
import ru.cactus.currency.presentation.screens.MainViewModel
import ru.cactus.currency.presentation.screens.components.AppBar
import ru.cactus.currency.presentation.screens.components.BottomNavigationBar

@Composable
internal fun BaseScreen(
    viewModel: MainViewModel = hiltViewModel(), navController: NavHostController
) {
    val uiState: StateUI by viewModel.homeScreenState.collectAsState()

    Scaffold(
        topBar = {
            AppBar(symbols = uiState.symbolsMap)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        NavGraph(navController = navController)
    }
}
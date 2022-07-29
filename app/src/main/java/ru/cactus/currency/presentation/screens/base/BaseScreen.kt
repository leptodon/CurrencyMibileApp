package ru.cactus.currency.presentation.screens.base

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.cactus.currency.presentation.entity.StateUI
import ru.cactus.currency.presentation.navigation.NavGraph
import ru.cactus.currency.presentation.screens.MainViewModel
import ru.cactus.currency.presentation.screens.components.AppBar
import ru.cactus.currency.presentation.screens.components.BottomNavigationBar

@Composable
fun BaseScreen(
    viewModel: MainViewModel = hiltViewModel(), navController: NavHostController
) {
    val uiState: StateUI by viewModel.homeScreenState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    viewModel.getSymbols()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    when (currentRoute.toString()) {
        "home" -> { viewModel.homeContent() }
        "favorite" -> { viewModel.favoriteContent() }
    }

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            AppBar(symbols = uiState.symbolsList)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        NavGraph(navController = navController)
    }
}
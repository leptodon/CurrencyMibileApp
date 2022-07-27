package ru.cactus.currency.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.cactus.currency.presentation.screens.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = Screens.Home.route
    ) {
        composable(Screens.Home.route) {
            HomeScreen()
        }

        composable(Screens.Favorite.route) {
//            val favorites: List<Favorite> by viewModel.favoritesList.collectAsState(initial = emptyList())
//            val currenciesRates: Map<String, String> by viewModel.ratesMap.collectAsState(
//                initial = emptyMap()
//            )
//
//            viewModel.getFavorites()
//            FavoriteScreen(symbols = favorites, rates = currenciesRates)
        }
    }
}
package ru.cactus.currency.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.cactus.currency.NavigationItem
import ru.cactus.currency.data.entity.Favorite
import ru.cactus.currency.presentation.component.AppBar
import ru.cactus.currency.presentation.theme.CurrencyTheme
import ru.cactus.currency.presentation.theme.DarkGrey100


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CurrencyTheme {
                MainScreen(viewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val symbols: Map<String, String> by viewModel.response.collectAsState(initial = emptyMap())
    val currenciesRates: Map<String, String> by viewModel.ratesMap.collectAsState(initial = emptyMap())

    val navController = rememberNavController()
    Scaffold(
        topBar = {
            AppBar(symbols = symbols, viewModel = viewModel)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Navigation(navController, symbols, currenciesRates, viewModel)
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Favorite
    )
    BottomNavigation(
        backgroundColor = DarkGrey100,
        contentColor = Color.White,
        modifier = Modifier
            .height(70.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                    )
                },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    symbols: Map<String, String>,
    rates: Map<String, String>,
    viewModel: MainViewModel
) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(symbols, rates) { viewModel.addToFavorite(it) }
        }

        composable(NavigationItem.Favorite.route) {
            val favorites: List<Favorite> by viewModel.favoritesList.collectAsState(initial = emptyList())
            val currenciesRates: Map<String, String> by viewModel.ratesMap.collectAsState(
                initial = emptyMap()
            )

            viewModel.getFavorites()
            FavoriteScreen(symbols = favorites, rates = currenciesRates)
        }
    }
}

/*private suspend fun fetchData() {
    mainViewModel.fetchSymbolsResponse()
    mainViewModel.response.collect() { response ->
        when (response) {
            is NetworkResult.Success -> {
                val symb: Symbols = response.data!!

                Log.d("TAG", symb.toString())
                // bind data to the view
            }
            is NetworkResult.Error -> {
                Log.d("TAG", response.message.toString())
                // show error message
            }
            is NetworkResult.Loading -> {
                Log.d("TAG", response.message.toString())
                // show a progress bar
            }
        }
    }*/


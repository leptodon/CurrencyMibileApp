package ru.cactus.currency.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.cactus.currency.presentation.navigation.NavGraph
import ru.cactus.currency.presentation.screens.base.BaseScreen
import ru.cactus.currency.ui.theme.CurrencyTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CurrencyTheme {
                navController = rememberNavController()
                BaseScreen(navController = navController)
            }
        }
    }
}

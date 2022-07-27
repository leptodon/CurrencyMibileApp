package ru.cactus.currency.presentation.navigation

import ru.cactus.currency.R

sealed class Screens(var route: String, var icon: Int, var title: String) {
    object Home : Screens("home", R.drawable.home, "Home")
    object Favorite : Screens("favorite", R.drawable.star, "Favorite")
}

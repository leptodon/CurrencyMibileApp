package ru.cactus.currency

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.home, "Home")
    object Favorite : NavigationItem("music", R.drawable.star, "Favorite")
}

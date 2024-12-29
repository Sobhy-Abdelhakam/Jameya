package dev.sobhy.jameya.navigation

enum class Screen {
    LOGIN,
    HOME
}
sealed class NavigationItem(val route: String){
    data object Login: NavigationItem(Screen.LOGIN.name)
    data object Home: NavigationItem(Screen.HOME.name)
}
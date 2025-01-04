package dev.sobhy.jameya.navigation

enum class Screen {
    LOGIN,
    VERIFY_PHONE,
    PROFILE,
    HOME
}
sealed class NavigationItem(val route: String){
    data object Login: NavigationItem(Screen.LOGIN.name)
    data object VerifyPhone: NavigationItem(Screen.VERIFY_PHONE.name)
    data object Profile: NavigationItem(Screen.PROFILE.name)
    data object Home: NavigationItem(Screen.HOME.name)
}
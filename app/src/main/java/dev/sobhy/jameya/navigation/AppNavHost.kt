package dev.sobhy.jameya.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.sobhy.jameya.presentation.home.HomeScreen
import dev.sobhy.jameya.presentation.login.LoginScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.Login.route) {
            LoginScreen(navController)
        }
        composable(NavigationItem.Home.route){
            HomeScreen()
        }
    }
}
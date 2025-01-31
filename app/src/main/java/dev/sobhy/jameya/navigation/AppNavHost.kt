package dev.sobhy.jameya.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.sobhy.jameya.presentation.addjameya.AddJameyaScreen
import dev.sobhy.jameya.presentation.details.JameyaDetailsScreen
import dev.sobhy.jameya.presentation.home.HomeScreen
import dev.sobhy.jameya.presentation.login.LoginScreen
import dev.sobhy.jameya.presentation.profile.ProfileScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.Login.route) {
            LoginScreen(navController)
        }
        composable(NavigationItem.Profile.route){
            ProfileScreen()
        }
        composable(NavigationItem.Home.route){
            HomeScreen(navController = navController)
        }
        composable(NavigationItem.Create.route){
            AddJameyaScreen(navController = navController)
        }
        composable(NavigationItem.Details.route){
            JameyaDetailsScreen(navController = navController)
        }
    }
}
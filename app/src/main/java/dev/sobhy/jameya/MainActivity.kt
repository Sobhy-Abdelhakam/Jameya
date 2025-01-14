package dev.sobhy.jameya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.sobhy.jameya.navigation.AppNavHost
import dev.sobhy.jameya.navigation.NavigationItem
import dev.sobhy.jameya.ui.theme.JameyaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isLoggedIn by viewModel.startDestination.collectAsStateWithLifecycle()
            val startDestination = when {
                isLoggedIn.isNullOrBlank() -> NavigationItem.Login.route
                else -> NavigationItem.Home.route
            }
            val navController = rememberNavController()
            JameyaTheme {
                Surface {
                    AppNavHost(navController = navController, startDestination = startDestination)
                }
            }
        }
    }
}
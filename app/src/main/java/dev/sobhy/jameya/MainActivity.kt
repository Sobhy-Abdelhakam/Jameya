package dev.sobhy.jameya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.sobhy.jameya.navigation.AppNavHost
import dev.sobhy.jameya.ui.theme.JameyaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen.setKeepOnScreenCondition {
            viewModel.uiState.value is StartDestinationState.Loading
        }
        setContent {
            val uiState by viewModel.uiState.collectAsState()
            val navController = rememberNavController()
            JameyaTheme {
                Surface {
                    if (uiState is StartDestinationState.Destination){
                        val startDestination = (uiState as StartDestinationState.Destination).route
                        AppNavHost(navController = navController, startDestination = startDestination)
                    }
                }
            }
        }
    }
}
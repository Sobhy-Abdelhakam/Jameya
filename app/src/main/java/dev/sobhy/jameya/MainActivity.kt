package dev.sobhy.jameya

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.sobhy.jameya.navigation.AppNavHost
import dev.sobhy.jameya.presentation.login.LoginScreen
import dev.sobhy.jameya.ui.theme.JameyaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()
            val navController = rememberNavController()
            JameyaTheme {
                Surface {
                    val url = BuildConfig.SUPABASE_URL
                    val key = BuildConfig.SUPABASE_KEY
                    Log.d("MainActivity", "onCreate: $url $key")
                    AppNavHost(navController = navController, startDestination = startDestination!!)
                }
            }
        }
    }
}
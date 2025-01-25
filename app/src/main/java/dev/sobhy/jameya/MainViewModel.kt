package dev.sobhy.jameya

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sobhy.jameya.data.datastore.DataStoreManager
import dev.sobhy.jameya.domain.usecase.CheckIfUserLoggedInUseCase
import dev.sobhy.jameya.domain.usecase.RefreshSessionUseCase
import dev.sobhy.jameya.domain.usecase.RetrieveUserUseCase
import dev.sobhy.jameya.navigation.NavigationItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkIfUserLoggedInUseCase: CheckIfUserLoggedInUseCase,
    private val retrieveUserUseCase: RetrieveUserUseCase,
    private val refreshSessionUseCase: RefreshSessionUseCase,
) : ViewModel() {
    private val _startDestination = MutableStateFlow<String>(NavigationItem.Login.route)
    val startDestination: StateFlow<String> = _startDestination
    private val _uiState = MutableStateFlow<StartDestinationState>(StartDestinationState.Loading)
    val uiState: StateFlow<StartDestinationState> = _uiState

    init {
        viewModelScope.launch {
            determineStartDestination()
        }
    }

    private suspend fun determineStartDestination() {
        if (checkIfUserLoggedInUseCase()) {
            Log.d("loggedIn", "User is logged in")
            try {
                retrieveUserUseCase()
                refreshSessionUseCase()
                _uiState.value = StartDestinationState.Destination(NavigationItem.Home.route)
            } catch (e: Exception) {
                _uiState.value = StartDestinationState.Destination(NavigationItem.Login.route)
            }
        } else {
            Log.d("loggedIn", "user not logged in")
            _uiState.value = StartDestinationState.Destination(NavigationItem.Login.route)
        }
    }
}
sealed class StartDestinationState {
    object Loading : StartDestinationState()
    data class Destination(val route: String) : StartDestinationState()
}

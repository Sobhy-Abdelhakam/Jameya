package dev.sobhy.jameya

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sobhy.jameya.domain.usecase.CheckIfUserLoggedInUseCase
import dev.sobhy.jameya.domain.usecase.RefreshSessionUseCase
import dev.sobhy.jameya.navigation.NavigationItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkIfUserLoggedInUseCase: CheckIfUserLoggedInUseCase,
    private val refreshSessionUseCase: RefreshSessionUseCase,
) : ViewModel() {
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
                refreshSessionUseCase()
//                retrieveUserUseCase()
                _uiState.value = StartDestinationState.Destination(NavigationItem.Home.route)
            } catch (e: Exception) {
                Log.e("error in login", e.message.toString())
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

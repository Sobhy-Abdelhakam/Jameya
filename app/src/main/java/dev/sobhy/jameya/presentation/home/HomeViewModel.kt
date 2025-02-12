package dev.sobhy.jameya.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sobhy.jameya.domain.model.User
import dev.sobhy.jameya.domain.usecase.RetrieveUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val retrieveUser: RetrieveUserUseCase
): ViewModel() {
    var user by mutableStateOf<User?>(null)
        private set
    init {
        getUser()
    }
    fun getUser() {
        viewModelScope.launch {
            user = retrieveUser.invoke()
        }
    }
}
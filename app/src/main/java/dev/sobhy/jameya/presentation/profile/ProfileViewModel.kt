package dev.sobhy.jameya.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sobhy.jameya.core.response.ApiResource
import dev.sobhy.jameya.domain.model.User
import dev.sobhy.jameya.domain.usecase.GetUserUseCase
import dev.sobhy.jameya.domain.usecase.UpdateImageUseCase
import dev.sobhy.jameya.domain.usecase.UpdateNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateNameUseCase: UpdateNameUseCase,
    private val updateImageUseCase: UpdateImageUseCase
): ViewModel(){
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> get() = _state

    init {
        fetchUser()
    }

    fun fetchUser() {
        viewModelScope.launch {
            getUserUseCase.execute()
                .onStart { _state.value = ProfileState(isLoading = true) }
                .catch { _state.value = ProfileState(error = it.message) }
                .collect {
                    when(it){
                        is ApiResource.Error -> _state.value = ProfileState(error = it.errorBody)
                        ApiResource.Loading -> _state.value = ProfileState(isLoading = true)
                        is ApiResource.Success -> _state.value = ProfileState(user = it.data)
                    }
                }
        }
    }
}

data class ProfileState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
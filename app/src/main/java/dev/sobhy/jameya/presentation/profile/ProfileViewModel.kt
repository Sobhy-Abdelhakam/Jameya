package dev.sobhy.jameya.presentation.profile

import android.util.Log
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateNameUseCase: UpdateNameUseCase,
    private val updateImageUseCase: UpdateImageUseCase
): ViewModel(){
    private val _state = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val state: StateFlow<ProfileState> get() = _state

    init {
        fetchUser()
    }

    fun fetchUser() {
        viewModelScope.launch {
            getUserUseCase.execute()
                .catch { _state.value = ProfileState.Error(it.message) }
                .collect {
                    Log.d("responseState", it.toString())
                    when(it){
                        is ApiResource.Error -> _state.value = ProfileState.Error(it.errorBody)
                        ApiResource.Loading -> _state.value = ProfileState.Loading
                        is ApiResource.Success -> _state.value = ProfileState.Success(it.data)
                    }
                }
        }
    }
}

sealed class ProfileState {
    data class Success(val user: User?): ProfileState()
    data class Error(val message: String?): ProfileState()
    object Loading: ProfileState()
}

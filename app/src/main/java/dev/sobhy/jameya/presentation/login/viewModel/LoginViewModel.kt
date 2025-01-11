package dev.sobhy.jameya.presentation.login.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sobhy.jameya.domain.usecase.SendOtpUseCase
import dev.sobhy.jameya.domain.usecase.VerifyOtpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sendOtpUseCase: SendOtpUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun handleEvent(event: LoginEvent){
        when(event){
            is LoginEvent.UpdatePhoneNumber -> updatePhoneNumber(event.phoneNumber)
            is LoginEvent.UpdateOtp -> updateOtp(event.otp)
            LoginEvent.ConfirmNumber -> sendOtp()
            LoginEvent.VerifyOtp -> verifyOtp()
            LoginEvent.DismissErrorDialog -> dismissErrorDialog()
            LoginEvent.NavigateToPhoneNumber -> navigateToPhoneNumber()
            LoginEvent.ResendOtp -> sendOtp()
        }
    }
    var phoneNumber: String by mutableStateOf("")
        private set
    var otp: String by mutableStateOf("")
        private set

    private fun updatePhoneNumber(newNumber: String) {
        _uiState.update { it.copy(phoneNumber = newNumber) }
    }
    private fun updateOtp(newOtp: String) {
        _uiState.update { it.copy(otp = newOtp) }
    }

    private fun sendOtp() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = sendOtpUseCase("+20${uiState.value.phoneNumber}")
            _uiState.update {
                if (result.isSuccess) {
                    it.copy(isLoading = false, error = null, currentContent = ScreenContent.Otp)
                } else {
                    it.copy(isLoading = false, error = result.exceptionOrNull()?.message ?: "Failed to send OTP")
                }
            }
        }
    }
    private fun verifyOtp() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = verifyOtpUseCase("+20${uiState.value.phoneNumber}", uiState.value.otp)
            _uiState.update {
                if (result.isSuccess) {
                    it.copy(isSuccess = true, isLoading = false, error = null)
                } else {
                    it.copy(
                        isLoading = false,
                        error = result.exceptionOrNull()?.message ?: "Failed to verify OTP"
                    )
                }
            }
        }
    }
    private fun navigateToPhoneNumber() {
        _uiState.update { it.copy(currentContent = ScreenContent.PhoneNumber) }
    }
    private fun dismissErrorDialog() {
        _uiState.update { it.copy(error = null) }
    }
}


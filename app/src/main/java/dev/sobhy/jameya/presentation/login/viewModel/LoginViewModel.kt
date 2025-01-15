package dev.sobhy.jameya.presentation.login.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sobhy.jameya.domain.usecase.SendOtpUseCase
import dev.sobhy.jameya.domain.usecase.VerifyOtpUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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
    private var countdownJob: Job? = null

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
                    it.copy(isLoading = false, error = null, currentContent = ScreenContent.Otp).apply {
                        startCountdown()
                    }
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
    private fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            for (time in 59 downTo 0) {
                delay(1000L)
                _uiState.update { it.copy(remainingTime = time) }
                Log.d("Counter", time.toString())
            }
        }
    }
}


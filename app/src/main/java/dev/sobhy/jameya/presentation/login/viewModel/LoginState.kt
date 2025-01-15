package dev.sobhy.jameya.presentation.login.viewModel

sealed class ScreenContent{
    data object PhoneNumber: ScreenContent()
    data object Otp: ScreenContent()
}

data class LoginUiState(
    val currentContent:ScreenContent = ScreenContent.PhoneNumber,
    val phoneNumber: String = "",
    val otp: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,
    val remainingTime: Int = 0
)
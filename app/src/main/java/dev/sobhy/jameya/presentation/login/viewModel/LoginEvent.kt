package dev.sobhy.jameya.presentation.login.viewModel

sealed class LoginEvent{
    data class UpdatePhoneNumber(val phoneNumber: String) : LoginEvent()
    data class UpdateOtp(val otp: String) : LoginEvent()
    data object ConfirmNumber : LoginEvent()
    data object VerifyOtp : LoginEvent()
    data object ResendOtp : LoginEvent()
    data object NavigateToPhoneNumber : LoginEvent()
    data object DismissErrorDialog : LoginEvent()
}
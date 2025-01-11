package dev.sobhy.jameya.presentation.login.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import dev.sobhy.jameya.presentation.login.viewModel.ScreenContent

@Composable
fun LoginScreenContent(
    currentContent: ScreenContent,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onSendOtp: () -> Unit,
    otp: String,
    onOTPChanged: (String) -> Unit,
    onWrongNumberClicked: () -> Unit,
    onRetry: () -> Unit,
    onVerifyOtp: () -> Unit,
) {
    val screenTitle = remember(currentContent) {
        if (currentContent is ScreenContent.PhoneNumber) "Enter your phone number" else "Verify your number"
    }
    val screenSupTitle = remember(currentContent, phoneNumber) {
        if (currentContent is ScreenContent.PhoneNumber) {
            "We will send you an OTP to verify your number"
        } else {
            "You've tried to sign in with +20${phoneNumber}.\nEnter the OTP sent to your number"
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LoginTopSection(title = screenTitle, text = screenSupTitle)
        when(currentContent){
            ScreenContent.PhoneNumber -> PhoneContent(
                phoneNumber = phoneNumber,
                onPhoneNumberChange = onPhoneNumberChange,
                onConfirm = onSendOtp
            )
            ScreenContent.Otp -> OtpContent(
                otp = otp,
                onOtpChange = onOTPChanged,
                onSubmit = onVerifyOtp,
                onWrongNumber = onWrongNumberClicked,
                onRetry = onRetry
            )
        }
    }
}
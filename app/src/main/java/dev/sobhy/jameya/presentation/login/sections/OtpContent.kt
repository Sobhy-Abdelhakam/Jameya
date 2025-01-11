package dev.sobhy.jameya.presentation.login.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.sobhy.jameya.presentation.login.components.OTPTextField

@Composable
fun OtpContent(
    otp: String,
    onOtpChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onWrongNumber: () -> Unit,
    onRetry: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = onWrongNumber) {
            Text("Wrong number?")
        }
        OTPTextField(otp = otp, onOTPChanged = onOtpChange, onDone = onSubmit)
        Button(onClick = onSubmit, modifier = Modifier.fillMaxWidth()) {
            Text("Verify OTP")
        }
        TextButton(onClick = onRetry) {
            Text("Didn't receive OTP?")
        }

    }
}
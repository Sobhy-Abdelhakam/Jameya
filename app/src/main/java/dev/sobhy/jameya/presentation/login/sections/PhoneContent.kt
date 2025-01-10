package dev.sobhy.jameya.presentation.login.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PhoneContent(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onConfirm: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginTopSection(title = "Enter you phone number", text = "We will send you an OTP to verify your number")
        PhoneNumberSection(phoneNumber =phoneNumber, onPhoneNumberChange =onPhoneNumberChange)
        Button(onClick = onConfirm, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Send OTP", modifier = Modifier.padding(4.dp))
        }
    }
}
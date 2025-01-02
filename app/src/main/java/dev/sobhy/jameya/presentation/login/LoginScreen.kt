package dev.sobhy.jameya.presentation.login

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.sobhy.jameya.presentation.login.sections.ConfirmPhoneNumberDialog
import dev.sobhy.jameya.presentation.login.sections.LoginTopSection
import dev.sobhy.jameya.presentation.login.sections.PhoneNumberSection
import java.util.concurrent.TimeUnit

@Composable
fun LoginScreen(navController: NavHostController) {
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var showConfirmDialog by remember {
        mutableStateOf(false)
    }
    var otp by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current as Activity
    Scaffold { paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .imePadding()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Spacer(modifier = Modifier.weight(1f))
            LoginTopSection()
            Spacer(modifier = Modifier.height(32.dp))
            PhoneNumberSection(
                phoneNumber = phoneNumber,
                onPhoneNumberChange = { phoneNumber = it }
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { showConfirmDialog = true }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Send OTP", modifier = Modifier.padding(4.dp))
            }
            Text(text = "OTP $otp", style = MaterialTheme.typography.titleMedium)
            ConfirmPhoneNumberDialog(
                showDialog = showConfirmDialog,
                number = phoneNumber,
                dismiss = { showConfirmDialog = false },
                confirm = {
                    showConfirmDialog = false
                }
            )
        }
    }
}
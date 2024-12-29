package dev.sobhy.jameya.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.sobhy.jameya.navigation.NavigationItem
import dev.sobhy.jameya.presentation.login.sections.ConfirmPhoneNumberDialog
import dev.sobhy.jameya.presentation.login.sections.LoginTopSection
import dev.sobhy.jameya.presentation.login.sections.PhoneNumberSection

@Composable
fun LoginScreen(navController: NavHostController) {
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var showConfirmDialog by remember {
        mutableStateOf(false)
    }
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
            ConfirmPhoneNumberDialog(
                showDialog = showConfirmDialog,
                number = phoneNumber,
                dismiss = { showConfirmDialog = false },
                confirm = {
                    showConfirmDialog = false
                    navController.navigate(NavigationItem.Home.route)
                }
            )
        }
    }
}
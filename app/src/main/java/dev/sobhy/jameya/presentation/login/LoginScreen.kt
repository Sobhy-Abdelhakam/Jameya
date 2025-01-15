package dev.sobhy.jameya.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.sobhy.jameya.navigation.NavigationItem
import dev.sobhy.jameya.presentation.login.components.ConfirmPhoneNumberDialog
import dev.sobhy.jameya.presentation.login.sections.LoginScreenContent
import dev.sobhy.jameya.presentation.login.viewModel.LoginEvent
import dev.sobhy.jameya.presentation.login.viewModel.LoginViewModel
import dev.sobhy.jameya.ui.compnents.ErrorDialog
import dev.sobhy.jameya.ui.compnents.LoadingDialog

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showConfirmDialog by remember { mutableStateOf(false) }
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(32.dp)
        ) {
            LoginScreenContent(
                currentContent = uiState.currentContent,
                phoneNumber = uiState.phoneNumber,
                onPhoneNumberChange = { viewModel.handleEvent(LoginEvent.UpdatePhoneNumber(it)) },
                onSendOtp = {
                    if (uiState.phoneNumber.startsWith("0")) {
                        viewModel.handleEvent(
                            LoginEvent.UpdatePhoneNumber(
                                uiState.phoneNumber.removePrefix(
                                    "0"
                                )
                            )
                        )
                    }
                    showConfirmDialog = true
                },
                otp = uiState.otp,
                onOTPChanged = { viewModel.handleEvent(LoginEvent.UpdateOtp(it)) },
                onWrongNumberClicked = { viewModel.handleEvent(LoginEvent.NavigateToPhoneNumber) },
                onRetry = { viewModel.handleEvent(LoginEvent.ResendOtp) },
                onVerifyOtp = { viewModel.handleEvent(LoginEvent.VerifyOtp) },
                remainingTime = uiState.remainingTime
            )
            if (showConfirmDialog) {
                ConfirmPhoneNumberDialog(
                    number = uiState.phoneNumber,
                    dismiss = { showConfirmDialog = false }) {
                    viewModel.handleEvent(LoginEvent.ConfirmNumber)
                    showConfirmDialog = false
                }
            }
            if (uiState.isLoading) {
                LoadingDialog()
            }
            uiState.error?.let { error ->
                ErrorDialog(message = error) {
                    viewModel.handleEvent(LoginEvent.DismissErrorDialog)
                }
            }
            if (uiState.isSuccess) {
                LaunchedEffect(Unit) {
                    navController.navigate(NavigationItem.Home.route) {
                        popUpTo(NavigationItem.Login.route) { inclusive = true }
                    }
                }
            }
        }
    }
}
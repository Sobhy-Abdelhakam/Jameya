package dev.sobhy.jameya.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.sobhy.jameya.navigation.NavigationItem
import dev.sobhy.jameya.presentation.login.sections.LoginScreenContent
import dev.sobhy.jameya.presentation.login.viewModel.LoginEvent
import dev.sobhy.jameya.presentation.login.viewModel.LoginViewModel
import dev.sobhy.jameya.presentation.login.viewModel.ScreenContent
import dev.sobhy.jameya.ui.compnents.ErrorDialog
import dev.sobhy.jameya.ui.compnents.LoadingDialog

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
                onPhoneNumberChange = {viewModel.handleEvent(LoginEvent.UpdatePhoneNumber(it))},
                onSendOtp = { viewModel.handleEvent(LoginEvent.ConfirmNumber) },
                otp = uiState.otp,
                onOTPChanged = { viewModel.handleEvent(LoginEvent.UpdateOtp(it)) },
                onWrongNumberClicked = { viewModel.handleEvent(LoginEvent.NavigateToPhoneNumber) },
                onRetry = { viewModel.handleEvent(LoginEvent.ResendOtp) },
                onVerifyOtp = { viewModel.handleEvent(LoginEvent.VerifyOtp) },
            )
            if (uiState.isLoading) {
                LoadingDialog()
            }
            uiState.error?.let { error ->
                ErrorDialog(message = error)
            }
            if (uiState.isSuccess) {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }
    }
}
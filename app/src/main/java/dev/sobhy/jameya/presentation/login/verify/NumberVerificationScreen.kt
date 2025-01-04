package dev.sobhy.jameya.presentation.login.verify

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.sobhy.jameya.navigation.NavigationItem
import dev.sobhy.jameya.presentation.login.sections.LoginTopSection

@Composable
fun NumberVerificationScreen(
    navController: NavHostController,
    phoneNumber: String,
) {
    var otp by remember {
        mutableStateOf("")
    }
    Scaffold { paddingValue ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValue)
                .padding(32.dp)
        ) {
            LoginTopSection(
                title = "OTP Verification",
                text = "You've tried to sign in with $phoneNumber.\n Please enter the OTP sent to your number."
            )
            Spacer(modifier = Modifier.height(32.dp))
            OTPTextField(
                otp = otp,
                onOTPChanged = { otp = it },
                onDone = {
                    navController.navigate(NavigationItem.Home.route)
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    // Handle OTP verification
                    navController.navigate(NavigationItem.Home.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Verify",
                    style = LocalTextStyle.current.copy(
                        color = Color.White
                    )
                )
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Verify"
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Didn't receive the OTP?",
                )
            }
        }
    }
}
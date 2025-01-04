package dev.sobhy.jameya.presentation.login.verify

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OTPTextField(
    otp: String,
    onOTPChanged: (String) -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = otp,
        onValueChange = { value -> if (value.length <= 6) onOTPChanged(value) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        ),
        modifier = Modifier.fillMaxWidth(),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(6) { index ->
                    val isFocused = otp.length == index
                    Text(
                        text = otp.getOrElse(index) { ' ' }.toString(),
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .width(40.dp)
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) Color.DarkGray else Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}
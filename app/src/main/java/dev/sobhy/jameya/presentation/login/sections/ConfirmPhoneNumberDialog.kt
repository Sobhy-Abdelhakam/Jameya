package dev.sobhy.jameya.presentation.login.sections

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ConfirmPhoneNumberDialog(
    showDialog: Boolean,
    number: String,
    dismiss: () -> Unit,
    confirm: () -> Unit,
) {
    if (!showDialog) return
    AlertDialog(
        onDismissRequest = dismiss,
        confirmButton = {
            TextButton(onClick = confirm) {
                Text(text = "Yes")
            }
        },
        title = {
            Text(text = "Is this the correct number?", style = MaterialTheme.typography.titleMedium)
        },
        text = {
            Text(text = "+20 $number", style = MaterialTheme.typography.headlineSmall)
        },
        dismissButton = {
            TextButton(onClick = dismiss) {
                Text(text = "Edit")
            }
        },
    )
}
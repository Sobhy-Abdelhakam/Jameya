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
    val titleText =
        if (number.isNotEmpty()) "Is this the correct number?" else "Please enter your phone number."
    val dismissText = if (number.isNotEmpty()) "Edit" else "Ok"

    AlertDialog(
        onDismissRequest = dismiss,
        title = { DialogTitle(text = titleText) },
        text = { DialogContent(number = number) },
        confirmButton = { DialogConfirmButton(number = number, onConfirm = confirm) },
        dismissButton = { DialogDismissButton(text = dismissText, onDismiss = dismiss) }
    )
}

@Composable
private fun DialogTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun DialogContent(number: String) {
    if (number.isNotEmpty()) {
        Text(
            text = "+20 $number",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
private fun DialogConfirmButton(number: String, onConfirm: () -> Unit) {
    if (number.isNotEmpty()) {
        TextButton(onClick = onConfirm) {
            Text(text = "Yes")
        }
    }
}

@Composable
private fun DialogDismissButton(text: String, onDismiss: () -> Unit) {
    TextButton(onClick = onDismiss) {
        Text(text = text)
    }
}
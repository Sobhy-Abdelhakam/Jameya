package dev.sobhy.jameya.presentation.login.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ConfirmPhoneNumberDialog(
    number: String,
    dismiss: () -> Unit,
    confirm: () -> Unit,
) {
    val titleText = when {
        number.isEmpty() -> "Enter your phone number"
        number.length < 10 -> "The phone number you entered is too short"
        number.length > 10 -> "The phone number you entered is too long"
        else -> "Confirm phone number"
    }
    val isNumberValid = number.isNotEmpty() && number.length == 10
    val dismissText = if (!isNumberValid) "Ok" else "Edit"

    AlertDialog(
        onDismissRequest = {},
        title = { DialogTitle(text = titleText) },
        text = {
            if (isNumberValid) {
                DialogContent(number = number)
            }
        },
        confirmButton = {
            if (isNumberValid) {
                DialogConfirmButton(onConfirm = confirm)
            }
        },
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
    Text(
        text = "+20 $number",
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun DialogConfirmButton(onConfirm: () -> Unit) {
    TextButton(onClick = onConfirm) {
        Text(text = "Yes")
    }
}

@Composable
private fun DialogDismissButton(text: String, onDismiss: () -> Unit) {
    TextButton(onClick = onDismiss) {
        Text(text = text)
    }
}
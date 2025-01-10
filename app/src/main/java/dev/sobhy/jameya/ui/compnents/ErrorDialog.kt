package dev.sobhy.jameya.ui.compnents

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorDialog(message: String) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Error") },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = {}) {
                Text(text = "OK")
            }
        }
    )
}
package dev.sobhy.jameya.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UpdateNameBottomSheet(
    dismissBottomSheet: () -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    save: () -> Unit,
) {
    UpdateInfoBottomSheet(dismissBottomSheet = dismissBottomSheet, title = "Enter your name") {
        Column(modifier = modifier.padding(16.dp)) {
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                modifier = modifier
            )
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.End) {
                TextButton(onClick = dismissBottomSheet) {
                    Text("Cancel")
                }
                TextButton(onClick = save) {
                    Text("Save")
                }
            }
        }
    }
}
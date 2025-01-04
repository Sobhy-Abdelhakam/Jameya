@file:OptIn(ExperimentalMaterial3Api::class)

package dev.sobhy.jameya.presentation.addjameya.components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import java.time.LocalDate

@Composable
fun CustomDatePicker(
    dismiss: () -> Unit,
    confirm: (LocalDate) -> Unit,
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = dismiss,
        confirmButton = {
            Button(
                onClick = {
                    dismiss()
                    val time = datePickerState.selectedDateMillis
                    val date = LocalDate.ofEpochDay(time!!/(1000 * 60 * 60 * 24))
                    confirm(date)
                },
                enabled = datePickerState.selectedDateMillis != null
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = dismiss,
            ) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
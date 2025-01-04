package dev.sobhy.jameya.presentation.addjameya

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.sobhy.jameya.presentation.addjameya.components.DatePickerTextField
import dev.sobhy.jameya.presentation.addjameya.components.DropDownMenuTextField
import java.time.LocalDate

@Composable
fun AddJameyaForm(
    modifier: Modifier = Modifier,
    jameyaName: String,
    onJameyaNameChange: (String) -> Unit,
    frequency: String,
    onFrequencyChange: (String) -> Unit,
    startDate: String,
    onStartDateChange: (LocalDate) -> Unit,
    endDate: String,
    onEndDateChange: (LocalDate) -> Unit,
    amount: String,
    onAmountChange: (String) -> Unit,
    buttonClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = jameyaName,
            onValueChange = onJameyaNameChange,
            label = { Text("Jameya Name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        DropDownMenuTextField(
            label = "Frequency",
            options = listOf("Weekly", "Monthly"),
            selectedOption = frequency,
            onOptionSelected = onFrequencyChange,
            modifier = Modifier.fillMaxWidth(),
        )
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            DatePickerTextField(
                value = startDate,
                onValueChange = onStartDateChange,
                label = "Start Date",
                modifier = Modifier
                    .weight(1f),
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Forward",
                modifier = Modifier.padding(8.dp)
            )
            DatePickerTextField(
                value = endDate,
                onValueChange = onEndDateChange,
                label = "End Date",
                modifier = Modifier.weight(1f),
            )
        }
        OutlinedTextField(
            value = amount,
            onValueChange = onAmountChange,
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = buttonClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Create", modifier = Modifier.padding(4.dp))
        }
    }
}
package dev.sobhy.jameya.presentation.addjameya

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.sobhy.jameya.presentation.addjameya.components.DatePickerTextField
import dev.sobhy.jameya.presentation.addjameya.components.DropDownMenuTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddJameyaScreen(navController: NavHostController) {
    var jameyaName by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var frequency by remember { mutableStateOf("Select Frequency") }
    var amount by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Jameya") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        }
    ) { paddingValues ->
        AddJameyaForm(
            jameyaName = jameyaName,
            onJameyaNameChange = { jameyaName = it },
            frequency = frequency,
            onFrequencyChange = { frequency = it },
            startDate = startDate,
            onStartDateChange = { startDate = it.toString() },
            endDate = endDate,
            onEndDateChange = { endDate = it.toString() },
            amount = amount,
            onAmountChange = { amount = it },
            modifier = Modifier.padding(paddingValues).padding(16.dp)
        ) {
            // Handle create Jameya
        }
    }
}
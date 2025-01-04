package dev.sobhy.jameya.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JameyaDetailsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Jameya Title") },
                actions = {
                    Text(text = "Status: Active", modifier = Modifier.padding(16.dp))
                    IconButton(onClick = { /* Handle more options click */ }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                    IconButton(onClick = { /* Handle share click */ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = null)
                    }
                    IconButton(onClick = { /* Handle share click */ }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Handle FAB click */ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            TabRow(selectedTabIndex = 0) {
                Tab(selected = true, onClick = { /* Handle Participants tab click */ }) {
                    Text("Participants")
                }
                Tab(selected = false, onClick = { /* Handle Payments tab click */ }) {
                    Text("Payments")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Content for Participants or Payments
            // Example for Participants
            LazyColumn {
                items(10) { index ->
                    ListItem(
                        headlineContent = { Text("Participant $index") },
                        supportingContent = { Text("Status: Paid") }
                    )
                }
            }
        }
    }
}
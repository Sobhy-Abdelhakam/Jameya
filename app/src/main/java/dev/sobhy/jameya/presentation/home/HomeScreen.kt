package dev.sobhy.jameya.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.sobhy.jameya.R
import dev.sobhy.jameya.navigation.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile"
                        )
                    }
                },
                title = {
                    Text(text = "Hi, Sobhy", style = MaterialTheme.typography.headlineSmall)
                },
                actions = {
                    BadgedBox(badge = { Badge { Text("2") } }, modifier = Modifier.padding(16.dp)) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notification",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(8.dp)) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Setting")
                    }

                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(NavigationItem.Create.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(30) {
                ListItem(
                    headlineContent = { Text("Jameya $it") },
                    supportingContent = {
                        Text(text = "$it members")
                    },
                    leadingContent = {
                        Image(
                            painter = painterResource(id = R.drawable.week),
                            contentDescription = "month",
                            modifier = Modifier
                                .padding(4.dp)
                                .size(50.dp)
                                .padding(4.dp)
                        )
                    },
                    trailingContent = {
                        Text(text = "12 May - 12 June")
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(NavigationItem.Details.route)
                        }
                )
            }
        }
    }
}
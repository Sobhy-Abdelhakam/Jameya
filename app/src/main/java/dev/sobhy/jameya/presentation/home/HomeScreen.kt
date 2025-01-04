package dev.sobhy.jameya.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.sobhy.jameya.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
                 TopAppBar(
                     title = {
                        Text(text = "Jameya", style = MaterialTheme.typography.headlineMedium)
                    },
                     actions = {
                        BadgedBox(badge = { Badge { Text("2") } }) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
                            }
                        }
                     },
                     scrollBehavior = scrollBehavior
                 )
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 16.dp, end = 16.dp, top = 50.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "Hi, Sobhy",
//                    style = MaterialTheme.typography.headlineMedium
//                )
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
//                }
//
//            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
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
                                        Text(text ="$it members")
                    },
                    leadingContent = {
                        Image(
                            painter = painterResource(id = R.drawable.week),
                            contentDescription = "month",
                            modifier = Modifier.padding(4.dp).size(50.dp).padding(4.dp)
                        )
                    },
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
    }
}
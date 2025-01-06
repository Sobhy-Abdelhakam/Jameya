package dev.sobhy.jameya.presentation.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreenContent(modifier: Modifier = Modifier) {
    var showBottomSheet by remember { mutableStateOf(false) }
    Column(modifier = modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        ProfileImageWithEditIcon(
            imageUrl = "https://image.tmdb.org/t/p/original/9hGF3WUkBf7cSjMg0cdMDHJkByd.jpg",
            editIconClick = { showBottomSheet = true }
        )
        if (showBottomSheet){
            ChangeImageBottomSheet(dismissBottomSheet = { showBottomSheet = false }, imageUrl = null, imageChanged = {})
        }
        InformationRow(icon = Icons.Outlined.Person, title = "Name", value = "Sobhy")
    }
}


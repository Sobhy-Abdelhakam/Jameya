package dev.sobhy.jameya.presentation.profile.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import dev.sobhy.jameya.domain.model.User

@Composable
fun ProfileScreenContent(
    user: User?,
    modifier: Modifier = Modifier,
    imageChanged: (Uri?) -> Unit,
    updateName: (String) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var updateNameBottomSheet by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    Column(modifier = modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        ProfileImageWithEditIcon(
            imageUrl = user?.image,
            editIconClick = { showBottomSheet = true }
        )
        if (showBottomSheet) {
            ChangeImageBottomSheet(
                dismissBottomSheet = { showBottomSheet = false },
                enableDeleteIcon = user?.image != null,
                imageChanged = imageChanged
            )
        }
        InformationRow(
            icon = Icons.Outlined.Person,
            title = "Name",
            value = user?.name ?: "null",
            modifier = Modifier.clickable {
                updateNameBottomSheet = true
            }
        )
        if (updateNameBottomSheet){
            UpdateNameBottomSheet(
                dismissBottomSheet = { updateNameBottomSheet = false},
                name = name,
                onNameChange = {name = it},
                modifier = Modifier.fillMaxWidth(),
                save = { updateName(name) }
            )
        }
    }
}


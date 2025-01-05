package dev.sobhy.jameya.presentation.profile.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.sobhy.jameya.R

@Composable
fun ProfileImageWithEditIcon(
    imageUri: Uri?,
    editIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.size(150.dp)) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile image",
            modifier = modifier
                .fillMaxSize()
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = editIconClick,
            colors = IconButtonDefaults.filledIconButtonColors(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(50.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                )
        ) {
            Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Edit")
        }
    }
}
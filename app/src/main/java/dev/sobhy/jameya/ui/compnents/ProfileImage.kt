package dev.sobhy.jameya.ui.compnents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.sobhy.jameya.R

@Composable
fun ProfileImage(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = imageUrl,
        placeholder = painterResource(id = R.drawable.profile),
        error = painterResource(id = R.drawable.profile),
        fallback = painterResource(id = R.drawable.profile),
        contentDescription = "Profile Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = CircleShape
            ),
    )
}
@file:OptIn(ExperimentalMaterial3Api::class)

package dev.sobhy.jameya.presentation.profile.components

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChangeImageBottomSheet(
    dismissBottomSheet: () -> Unit,
    imageUrl: String?,
    imageChanged: (Uri?) -> Unit,
) {
        ModalBottomSheet(
            onDismissRequest = dismissBottomSheet,
        ) {
            Text(
                text = "Profile photo",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Column(
                    modifier = Modifier.padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconButton(
                        onClick = {
//                            galleryLauncher.launch("image/*")
                            dismissBottomSheet()
                        },
                        modifier =
                        Modifier
                            .size(70.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = CircleShape,
                            ),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Image,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                    Text(text = "Gallery", modifier = Modifier.padding(8.dp))
                }
                Column(
                    modifier = Modifier.padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconButton(
                        onClick = {
                            imageChanged(null)
                            dismissBottomSheet()
                        },
                        modifier =
                        Modifier
                            .size(70.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = CircleShape,
                            ),
                        colors =
                        IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.error,
                            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            disabledContentColor = MaterialTheme.colorScheme.secondary,
                        ),
                        enabled = imageUrl != null,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Delete",
                        modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        }

}
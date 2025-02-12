@file:OptIn(ExperimentalMaterial3Api::class)

package dev.sobhy.jameya.presentation.profile.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UpdateInfoBottomSheet(
    dismissBottomSheet: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    ModalBottomSheet(onDismissRequest = dismissBottomSheet) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier.padding(16.dp),
        )
        content()
    }
}
@file:OptIn(ExperimentalMaterial3Api::class)

package dev.sobhy.jameya.presentation.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.sobhy.jameya.presentation.profile.components.ProfileScreenContent
import dev.sobhy.jameya.ui.compnents.ErrorDialog
import dev.sobhy.jameya.ui.compnents.LoadingDialog

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            )
        }
    ) { paddingValue ->
            ProfileScreenContent(user = state.value.user, modifier = Modifier.padding(paddingValue))
        state.value.error?.let {
            ErrorDialog(it) { viewModel.dismissErrorDialog()}
        }
        if (state.value.isLoading){
            LoadingDialog()
        }
    }
}
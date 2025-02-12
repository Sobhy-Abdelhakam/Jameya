@file:OptIn(ExperimentalMaterial3Api::class)

package dev.sobhy.jameya.presentation.profile

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.sobhy.jameya.presentation.profile.components.ProfileScreenContent
import dev.sobhy.jameya.ui.compnents.ErrorDialog
import dev.sobhy.jameya.ui.compnents.LoadingDialog

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            ProfileTopBar {
                navController.popBackStack()
            }
        }
    ) { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue)) {
            when (state.value) {
                ProfileState.Loading -> LoadingDialog()
                is ProfileState.Error -> {
                    val errorState = state.value as ProfileState.Error
                    ErrorDialog(errorState.message ?: "Error") { }
                }
                is ProfileState.Success -> {
                    val successState = state.value as ProfileState.Success
                    val user = successState.user
                    if (user != null) {
                        ProfileScreenContent(
                            user = user,
                            imageChanged = {
                                Log.d("Uri", it?.path.toString())
                                viewModel.uploadImage(it?.path!!, context.contentResolver, it)
                            },
                            updateName = {
                                viewModel.uploadName(it)
                            }
                        )
                    } else {
                        Text(
                            text = "No user data available",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun ProfileTopBar(navigateBack: ()-> Unit) {
    TopAppBar(
        title = { Text(text = "Profile") },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}
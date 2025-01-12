package dev.sobhy.jameya

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.sobhy.jameya.data.datastore.DataStoreManager
import dev.sobhy.jameya.navigation.NavigationItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(dataStoreManager: DataStoreManager) : ViewModel() {
    val startDestination = dataStoreManager.userId.map {userId ->
        if (userId.isNullOrBlank()) NavigationItem.Login.route else NavigationItem.Home.route
    }.stateIn(
        scope = viewModelScope,
        initialValue = runBlocking { dataStoreManager.userId.first() },
        started = SharingStarted.Eagerly
    )
}
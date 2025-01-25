package dev.sobhy.jameya.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")
class DataStoreManager(context: Context) {
    private val dataStore = context.dataStore
    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    }
    suspend fun saveToken(accessToken: String){
        dataStore.edit { preference ->
            preference[ACCESS_TOKEN_KEY] = accessToken
        }
    }
    val token: Flow<String?> = dataStore.data.map { preferences ->
        preferences[ACCESS_TOKEN_KEY]
    }

    suspend fun removeToken() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
        }
    }
}
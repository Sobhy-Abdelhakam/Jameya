package dev.sobhy.jameya.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")
class DataStoreManager(context: Context) {
    private val dataStore = context.dataStore
    companion object {
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }
    val userId: Flow<String?> = dataStore.data.map {preferences ->
        preferences[USER_ID_KEY]
    }
    suspend fun setUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }
    suspend fun saveTokens(accessToken: String, refreshToken: String){
        dataStore.edit { preference ->
            preference[ACCESS_TOKEN_KEY] = accessToken
            preference[REFRESH_TOKEN_KEY] = refreshToken
        }
    }
    suspend fun getTokens(): Pair<String?, String?>{
        val preferences = dataStore.data.firstOrNull() ?: return null to null
        val accessToken = preferences[ACCESS_TOKEN_KEY]
        val refreshToken = preferences[REFRESH_TOKEN_KEY]
        return accessToken to refreshToken
    }

    suspend fun clearUserId() {
        dataStore.edit { preferences ->
            preferences.remove(USER_ID_KEY)
        }
    }
}
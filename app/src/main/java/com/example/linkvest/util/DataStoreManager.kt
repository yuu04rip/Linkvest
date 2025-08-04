package com.example.linkvest.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore("onboarding_prefs")

object DataStoreManager {
    private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    private val USER_TOKEN = stringPreferencesKey("user_token") // Se vuoi salvare il token JWT

    suspend fun setOnboardingCompleted(context: Context, completed: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[ONBOARDING_COMPLETED] = completed
        }
    }

    suspend fun isOnboardingCompleted(context: Context): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs[ONBOARDING_COMPLETED] ?: false
    }

    // --- LOGIN STATUS ---

    suspend fun setLoggedIn(context: Context, loggedIn: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = loggedIn
        }
    }

    suspend fun isLoggedIn(context: Context): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs[IS_LOGGED_IN] ?: false
    }

    // --- TOKEN (opzionale) ---

    suspend fun saveUserToken(context: Context, token: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_TOKEN] = token
        }
    }

    suspend fun getUserToken(context: Context): String? {
        val prefs = context.dataStore.data.first()
        return prefs[USER_TOKEN]
    }

    suspend fun clearUserToken(context: Context) {
        context.dataStore.edit { prefs ->
            prefs.remove(USER_TOKEN)
        }
    }
}
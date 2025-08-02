package com.example.linkvest.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore("onboarding_prefs")

object DataStoreManager {
    private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")

    suspend fun setOnboardingCompleted(context: Context, completed: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[ONBOARDING_COMPLETED] = completed
        }
    }

    suspend fun isOnboardingCompleted(context: Context): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs[ONBOARDING_COMPLETED] ?: false
    }
}
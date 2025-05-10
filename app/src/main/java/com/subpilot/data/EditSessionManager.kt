package com.geekneuron.subpilot.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "edit_session")

object EditSessionManager {
    private val SESSION_ACTIVE = booleanPreferencesKey("session_active")
    private val LAST_SUBTITLE_PATH = stringPreferencesKey("last_subtitle_path")

    fun isSessionActive(context: Context): Flow<Boolean> =
        context.dataStore.data.map { prefs ->
            prefs[SESSION_ACTIVE] ?: false
        }

    fun getLastSubtitlePath(context: Context): Flow<String?> =
        context.dataStore.data.map { prefs ->
            prefs[LAST_SUBTITLE_PATH]
        }

    suspend fun saveSession(context: Context, path: String) {
        context.dataStore.edit { prefs ->
            prefs[SESSION_ACTIVE] = true
            prefs[LAST_SUBTITLE_PATH] = path
        }
    }

    suspend fun clearSession(context: Context) {
        context.dataStore.edit { prefs ->
            prefs[SESSION_ACTIVE] = false
            prefs.remove(LAST_SUBTITLE_PATH)
        }
    }
}

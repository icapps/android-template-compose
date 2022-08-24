package com.icapps.template.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class SettingStorage(private val context: Context) {

    companion object {

        private val Context.settingStorage: DataStore<Preferences> by preferencesDataStore("app_settings")

        // Setting keys
        private val SETTING_EXAMPLE = stringPreferencesKey("example")
    }

    val example: Flow<String> = context.settingStorage.data.map { preferences ->
        preferences[SETTING_EXAMPLE] ?: "example"
    }

    suspend fun storeLanguage(value: String) = context.settingStorage.edit { preferences ->
        preferences[SETTING_EXAMPLE] = value
    }

    suspend fun clear() {
        context.settingStorage.edit { preferences ->
            preferences.clear()
        }
    }
}

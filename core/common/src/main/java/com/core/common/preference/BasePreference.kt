package com.core.common.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

abstract class BasePreference(private val dataStore: DataStore<Preferences>){
    protected fun <T> Preferences.Key<T>.watchValue(defaultValue: T): Flow<PreferenceFindingResult<T>> {
        return dataStore.data
            .catch { PreferenceFindingResult.Error(it) }
            .map { preferences -> PreferenceFindingResult.Found(preferences[this] ?: defaultValue) }
    }

    protected fun <T> Preferences.Key<T>.watchValue(): Flow<PreferenceFindingResult<T?>> {
        return dataStore.data
            .catch { PreferenceFindingResult.Error(it) }
            .map { preferences -> PreferenceFindingResult.Found(preferences[this]) }
    }

    protected suspend fun <T> Preferences.Key<T>.findValue(defaultValue: T): PreferenceFindingResult<T> {
        return dataStore.data
            .catch { PreferenceFindingResult.Error(it) }
            .map { preferences -> PreferenceFindingResult.Found(preferences[this] ?: defaultValue) }
            .firstOrNull() ?: PreferenceFindingResult.Found(defaultValue)
    }

    protected suspend fun <T> Preferences.Key<T>.findValues(): PreferenceFindingResult<T> {
        return runCatching {
            val a = dataStore.data
            .catch { PreferenceFindingResult.Error(it) }
            .map { preferences -> PreferenceFindingResult.Found(preferences[this]!!) }
            PreferenceFindingResult.Found(a.first().data)
        }.getOrElse { PreferenceFindingResult.Error(it)}
    }



    protected suspend fun <T> Preferences.Key<T>.upsertValue(value: T) : PreferenceWritingResult{
        return runCatching {
            dataStore.edit { preferences -> preferences[this] = value }
            return PreferenceWritingResult.Saved
        }.getOrElse { PreferenceWritingResult.Error(it) }
    }
}
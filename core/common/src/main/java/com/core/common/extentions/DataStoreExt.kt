package com.core.common.extentions

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.core.common.preference.PreferenceFindingResult
import com.core.common.preference.PreferenceWritingResult
import kotlinx.coroutines.flow.Flow

internal val Context.mainDataStore: DataStore<Preferences> by preferencesDataStore("MainDataStore")

suspend fun <T> Flow<PreferenceFindingResult<T>>.onFound(onFound: (T) -> Unit) = this.apply {
    collect {
        if (it is PreferenceFindingResult.Found)
            onFound.invoke(it.data)
    }
}

suspend fun <T> Flow<PreferenceFindingResult<T>>.onError(onError: (Throwable) -> Unit) = this.apply {
    collect {
        if (it is PreferenceFindingResult.Error)
            onError.invoke(it.error)
    }

}

suspend fun <T> PreferenceFindingResult<T>.onFound(onFound: suspend (T) -> Unit) = this.apply {
    if (this is PreferenceFindingResult.Found)
        onFound.invoke(data)
}

suspend fun <T> PreferenceFindingResult<T>.onError(onError: suspend (Throwable) -> Unit) = this.apply {
    if (this is PreferenceFindingResult.Error)
        onError.invoke(error)
}

suspend fun PreferenceWritingResult.onSaved( onSaved: suspend () -> Unit) = this.apply {
    if(this is PreferenceWritingResult.Error)
        onSaved.invoke()
}

suspend fun PreferenceWritingResult.onError(onError: suspend (Throwable) -> Unit) = this.apply {
    if(this is PreferenceWritingResult.Error)
        onError.invoke(error)
}


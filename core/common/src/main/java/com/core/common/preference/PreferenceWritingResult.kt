package com.core.common.preference

sealed interface PreferenceWritingResult {
    data object Saved : PreferenceWritingResult
    data class Error(val error:Throwable) : PreferenceWritingResult
}
package com.core.common.preference

sealed interface PreferenceFindingResult<out T>{
    data class Found<T>(val data: T) : PreferenceFindingResult<T>
    data class Error(val error: Throwable) : PreferenceFindingResult<Nothing>
}
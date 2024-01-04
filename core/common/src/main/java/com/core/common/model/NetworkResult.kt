package com.core.common.model

sealed interface NetworkResult<out T> {

    data class Loading(val enable: Boolean) : NetworkResult<Nothing>

    data class Success<T>(val data: T?) : NetworkResult<T>

    data class Error(val error: Throwable) : NetworkResult<Nothing>

}

inline fun <T> NetworkResult<T>.onError(errorHandler: (Throwable) -> Unit): NetworkResult<T> =
    this.also {
        if (this is NetworkResult.Error) {
            errorHandler(error)
        }
    }

inline fun <T> NetworkResult<T>.onLoading(loadingHandler: (enable: Boolean) -> Unit): NetworkResult<T> =
    this.also {
        if (this is NetworkResult.Loading) {
            loadingHandler(enable)
        }
    }

inline fun <T> NetworkResult<T>.onSuccess(successHandler: (T?) -> Unit): NetworkResult<T> =
    this.also {
        if (this is NetworkResult.Success) {
            successHandler(data)
        }
    }
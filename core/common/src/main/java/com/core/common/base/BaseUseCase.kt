package com.core.common.base

import com.core.common.model.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase {
    protected fun <T>runUseCase(body: suspend () -> T) : Flow<NetworkResult<T>> {
        return flow {
            emit(NetworkResult.Loading(true))
            emit(NetworkResult.Success(body.invoke()))
            emit(NetworkResult.Loading(false))
        }.catch {
            emit(NetworkResult.Loading(false))
            emit(NetworkResult.Error(it))
        }.flowOn(Dispatchers.IO)
    }
}
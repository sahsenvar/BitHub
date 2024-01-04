package com.core.common.interfaces

import com.core.common.base.BaseState

interface StateObserver<STATE : BaseState> {
    suspend fun observeState(state: STATE)

}
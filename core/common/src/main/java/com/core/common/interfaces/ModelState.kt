package com.core.common.interfaces

import kotlinx.coroutines.flow.Flow

interface ModelState<STATE, INTENT> {

    val state: Flow<STATE>
    fun sendIntent(intent: INTENT)
}
package com.core.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.interfaces.ModelState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE: BaseState, INTENT: BaseIntent>: ViewModel(), ModelState<STATE, INTENT> {
    protected val _state = Channel<STATE>(Channel.UNLIMITED)
    protected val TAG = "GitHub"
    override val state: Flow<STATE>
        get() = _state.receiveAsFlow()

    override fun sendIntent(intent: INTENT) {
        viewModelScope.launch(Dispatchers.IO){
            handleIntent(intent)
        }
    }
    protected abstract suspend fun handleIntent(intent: INTENT)
}
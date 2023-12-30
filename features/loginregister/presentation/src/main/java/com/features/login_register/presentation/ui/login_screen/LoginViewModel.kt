package com.features.login_register.ui.login_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LoginViewModel() : ViewModel() {

    private val _events = Channel<LoginState>(Channel.UNLIMITED)
    val events = _events.receiveAsFlow()

    private val _loginResult = MutableLiveData<LoginIntent>()
    val loginResult: LiveData<LoginIntent> = _loginResult

    fun directToLoginWebView(username: String, password: String) {

    }

}
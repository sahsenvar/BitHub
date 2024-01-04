package com.features.login_register.presentation.ui.login_screen

import com.core.common.base.BaseState

sealed class LoginState: BaseState() {
    data class Loading(val enable: Boolean) : LoginState()
    data object NavigationToHomePage: LoginState()
    data class Exception(val message: String, val detail: String?) : LoginState()
}
package com.features.login_register.presentation.ui.login_register_screen

import com.core.common.base.BaseState

sealed class LoginRegisterState : BaseState(){
    data object NavigationToLoginPage : LoginRegisterState()
    data object NavigationToRegisterPage : LoginRegisterState()
}
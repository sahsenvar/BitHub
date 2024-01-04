package com.features.login_register.presentation.ui.login_register_screen

import com.core.common.base.BaseViewModel

class LoginRegisterViewModel : BaseViewModel<LoginRegisterState, LoginRegisterIntent>() {
    override suspend fun handleIntent(intent: LoginRegisterIntent) {
        when(intent){
            LoginRegisterIntent.DirectToLoginPage ->{
                _state.send(LoginRegisterState.NavigationToLoginPage)
            }
            LoginRegisterIntent.DirectToRegisterPage ->{
                _state.send(LoginRegisterState.NavigationToRegisterPage)
            }
        }
    }
}
package com.features.login_register.presentation.ui.login_register_screen

import com.core.common.base.BaseIntent

sealed class LoginRegisterIntent : BaseIntent(){
    data object DirectToLoginPage: LoginRegisterIntent()
    data object DirectToRegisterPage: LoginRegisterIntent()
}
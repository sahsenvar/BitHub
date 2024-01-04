package com.sahansenvar.bithub.ui

import com.core.common.base.BaseState

sealed class SplashState : BaseState() {
    data class Loading(val isEnable: Boolean) : SplashState()
    data object UserLoggedIn : SplashState()
    data object UserNotLoggedIn : SplashState()
}
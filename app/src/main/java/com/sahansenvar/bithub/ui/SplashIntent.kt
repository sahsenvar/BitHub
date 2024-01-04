package com.sahansenvar.bithub.ui

import com.core.common.base.BaseIntent

sealed class SplashIntent : BaseIntent(){
    data object CheckUserLoginInfo : SplashIntent()
}
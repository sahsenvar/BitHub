package com.features.login_register.presentation.ui.login_screen

import com.core.common.base.BaseIntent

/**
 * Authentication result : success (user details) or error message.
 */
sealed class LoginIntent : BaseIntent(){
    data object OpenToLoginWebView : LoginIntent()
    data object DirectToHomePage : LoginIntent()
    data class GetAccessToken(val codeQuery: String?) : LoginIntent()
}
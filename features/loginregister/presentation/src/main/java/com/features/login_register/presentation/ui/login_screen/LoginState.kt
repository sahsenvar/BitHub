package com.features.login_register.ui.login_screen

/**
 * Data validation state of the login form.
 */
data class LoginState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)
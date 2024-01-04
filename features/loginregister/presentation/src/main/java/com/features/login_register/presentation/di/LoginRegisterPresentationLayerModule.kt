package com.features.login_register.presentation.di

import com.features.login_register.presentation.ui.login_register_screen.LoginRegisterViewModel
import com.features.login_register.presentation.ui.login_screen.LoginViewModel
import com.features.login_register.presentation.ui.register_screen.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val loginRegisterPresentationLayerModule = module {
    viewModelOf(::LoginRegisterViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
}
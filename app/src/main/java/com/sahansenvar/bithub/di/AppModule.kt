package com.sahansenvar.bithub.di

import com.sahansenvar.bithub.ui.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<SplashViewModel> { SplashViewModel(get()) }
}
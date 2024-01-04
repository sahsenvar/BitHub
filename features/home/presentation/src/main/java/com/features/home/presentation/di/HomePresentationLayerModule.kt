package com.features.home.presentation.di

import com.features.home.presentation.ui.home_screen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationLayerModule = module{
    viewModelOf(::HomeViewModel)
}
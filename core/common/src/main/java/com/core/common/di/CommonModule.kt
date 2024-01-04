package com.core.common.di

import PreferenceHelper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val commonModule = module {
    factoryOf(::PreferenceHelper)
}
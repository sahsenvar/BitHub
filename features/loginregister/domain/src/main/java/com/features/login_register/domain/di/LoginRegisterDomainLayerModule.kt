package com.features.login_register.domain.di

import com.features.login_register.domain.usecase.GetUserAccessTokenUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val loginRegisterDomainLayerModule = module {
    factoryOf(::GetUserAccessTokenUseCase)
}
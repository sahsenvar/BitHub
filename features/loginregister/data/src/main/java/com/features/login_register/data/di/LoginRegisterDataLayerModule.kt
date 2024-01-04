package com.features.login_register.data.di

import com.core.api.remote.constant.NetworkConstants.RETROFIT_FOR_AUTH_CLIENT
import com.core.api.remote.di.createApiService
import com.features.login_register.data.repository.UserAuthRepositoryImpl
import com.features.login_register.data.service.UserAuthService
import com.features.login_register.domain.repository.UserAuthRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loginRegisterDataLayerModule = module {

    factory<UserAuthService> { createApiService(retrofit = get(named(RETROFIT_FOR_AUTH_CLIENT))) }

    factory<UserAuthRepository> { UserAuthRepositoryImpl(get()) }
}
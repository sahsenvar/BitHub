package com.features.home.data.di

import com.core.api.remote.constant.NetworkConstants.RETROFIT_FOR_GENERAL
import com.core.api.remote.di.createApiService
import com.features.home.data.services.HomeService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeDataLayerModule = module{
    factory { createApiService<HomeService>(retrofit = get(named(RETROFIT_FOR_GENERAL))) }
}
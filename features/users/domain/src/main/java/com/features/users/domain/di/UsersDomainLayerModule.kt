package com.features.users.domain.di

import com.features.users.domain.usecase.ChangeFavoriteStatusUseCase
import com.features.users.domain.usecase.GetFavoriteUsersUseCase
import com.features.users.domain.usecase.GetUserDetailUseCase
import com.features.users.domain.usecase.GetUsersUseCase
import com.features.users.domain.usecase.SearchUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val usersDomainLayerModule = module{
    factoryOf(::GetUsersUseCase)
    factoryOf(::ChangeFavoriteStatusUseCase)
    factoryOf(::GetFavoriteUsersUseCase)
    factoryOf(::GetUserDetailUseCase)
    factoryOf(::SearchUserUseCase)
}
package com.features.users.data.di

import com.features.users.data.repository.UsersRepositoryImpl
import com.features.users.domain.repository.UsersRepository
import org.koin.dsl.module

val userDataLayerModule = module {
    factory<UsersRepository> { UsersRepositoryImpl(get(), get()) }
}
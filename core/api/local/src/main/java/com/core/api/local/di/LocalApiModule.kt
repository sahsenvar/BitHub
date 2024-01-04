package com.core.api.local.di

import androidx.room.Room
import com.core.api.local.UsersDB
import com.core.api.local.datasource.UsersLocalDataSource
import com.core.api.local.datasource.UsersLocalDataSourceImpl
import org.koin.dsl.module

val localApiModule = module {
    single {
        Room.databaseBuilder(
            get(), UsersDB::class.java, UsersDB.NAME
        ).build().usersDao
    }

    single<UsersLocalDataSource> { UsersLocalDataSourceImpl(get()) }
}
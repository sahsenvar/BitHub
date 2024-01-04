package com.features.users.presentation.di

import com.features.users.presentation.ui.user_detail_page.UserDetailViewModel
import com.features.users.presentation.ui.users_page.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val usersPresentationLayerModule = module {
    viewModelOf(::UsersViewModel)
    viewModelOf(::UserDetailViewModel)

}
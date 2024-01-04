package com.features.home.presentation.ui.home_screen

import com.core.common.base.BaseState
import com.features.users.domain.model.UserDomainModel

sealed class HomeState : BaseState() {
    data class Loading(val isEnable: Boolean) : HomeState()
    data object NavigationToLogin : HomeState()
    data class Error(val message: String, val detail: String?) : HomeState()
    data class FavoriteUsersFound(val users: List<UserDomainModel>) : HomeState()
    data class NavigationToUserDetailFragment(val users: UserDomainModel) : HomeState()
    data object Empty : HomeState()

}
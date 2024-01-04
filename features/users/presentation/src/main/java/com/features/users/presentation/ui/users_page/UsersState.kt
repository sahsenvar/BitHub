package com.features.users.presentation.ui.users_page

import com.core.common.base.BaseState
import com.features.users.domain.model.UserDomainModel

sealed class UsersState: BaseState() {
    data class Loading(val isEnable: Boolean) : UsersState()
    data class Error(val message: String, val detail: String?) : UsersState()
    data class UsersFound(val users: List<UserDomainModel>?) : UsersState()
    data class UserChangedFavorite(val users: List<UserDomainModel>?) : UsersState()
    data class NavigationToUserDetail(val user: UserDomainModel) : UsersState()
    data object Empty : UsersState()


}
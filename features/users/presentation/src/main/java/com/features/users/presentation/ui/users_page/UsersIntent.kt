package com.features.users.presentation.ui.users_page

import com.core.common.base.BaseIntent
import com.features.users.domain.model.UserDomainModel

sealed class UsersIntent : BaseIntent(){
    data class GoToUserDetailFragment(val user: UserDomainModel) : UsersIntent()

    data class MarkUserAsFavorite(val user: UserDomainModel) : UsersIntent()
    data object GetUsers : UsersIntent()
    data class SearchUser(val username: String?) : UsersIntent()
    data object EmptySearch: UsersIntent()
}
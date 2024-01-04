package com.features.home.presentation.ui.home_screen

import com.core.common.base.BaseIntent
import com.features.users.domain.model.UserDomainModel

sealed class HomeIntent : BaseIntent() {
    data object GetFavoriteUsers : HomeIntent()
    data class ChangeFavoriteStatus(val user: UserDomainModel) : HomeIntent()
    data object ClearSearch : HomeIntent()

    data class GoToUserDetailFragment(val user: UserDomainModel) : HomeIntent()
}
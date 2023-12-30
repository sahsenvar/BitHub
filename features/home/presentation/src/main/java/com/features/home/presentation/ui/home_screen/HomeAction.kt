package com.features.home.presentation.ui.home_screen

import com.core.common.base.BaseAction

sealed class HomeAction : BaseAction() {
    data object GoToRepositoriesScreen: HomeAction()
    data object GoToAllUsersScreen: HomeAction()
    data object GoToProfileScreen: HomeAction()
    data object CleanSearch: HomeAction()

}
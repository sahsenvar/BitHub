package com.features.presentation.ui

import com.core.common.base.BaseAction

sealed class HomeAction : BaseAction() {
    data object GoToRepositoriesScreen: HomeAction()
    data object GoToAllUsersScreen: HomeAction()
    data object GoToProfileScreen: HomeAction()
    data object CleanSearch: HomeAction()

}
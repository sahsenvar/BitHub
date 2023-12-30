package com.features.presentation.ui

sealed class HomeAction {
    data object GoToRepositoriesScreen: HomeAction()
    data object GoToAllUsersScreen: HomeAction()
    data object GoToProfileScreen: HomeAction()
    data object CleanSearch: HomeAction()

}
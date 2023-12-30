package com.features.home.presentation.ui.home_screen

import com.core.common.base.BaseState

sealed class HomeState : BaseState() {
    data object Loading : HomeState()

}
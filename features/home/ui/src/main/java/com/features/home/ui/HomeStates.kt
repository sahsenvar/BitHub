package com.features.home.ui

import com.core.common.base.BaseState

sealed class HomeState : BaseState() {
    data object Loading : HomeState()

}
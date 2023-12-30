package com.features.presentation.ui

import com.core.common.base.BaseState

sealed class HomeState : BaseState() {
    data object Loading : HomeState()

}
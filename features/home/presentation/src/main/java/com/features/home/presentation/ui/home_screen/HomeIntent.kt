package com.features.home.presentation.ui.home_screen

import com.core.common.base.BaseIntent

sealed class HomeIntent : BaseIntent() {
    data object LoadAllRepositories : HomeIntent()
    data class SearchRepository(val name: String) : HomeIntent()
    data object ClearSearch : HomeIntent()
}
package com.features.home.presentation.ui.home_screen

import androidx.lifecycle.viewModelScope
import com.core.common.base.BaseViewModel
import com.core.common.model.onError
import com.core.common.model.onLoading
import com.core.common.model.onSuccess
import com.features.users.domain.model.UserDomainModel
import com.features.users.domain.usecase.ChangeFavoriteStatusUseCase
import com.features.users.domain.usecase.GetFavoriteUsersUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFavoriteUsersUseCase: GetFavoriteUsersUseCase,
    private val changeFavoriteStatusUseCase: ChangeFavoriteStatusUseCase
) : BaseViewModel<HomeState, HomeIntent>() {
    override suspend fun handleIntent(intent: HomeIntent) {
        when(intent){
            is HomeIntent.ClearSearch -> {}
            is HomeIntent.GetFavoriteUsers -> {
                getFavoriteUsers()
            }
            is HomeIntent.ChangeFavoriteStatus -> {
                changeFavoriteStatus(intent.user)
            }

            is HomeIntent.GoToUserDetailFragment -> {
                _state.send(HomeState.NavigationToUserDetailFragment(intent.user))
            }
        }
    }

    private fun changeFavoriteStatus(user: UserDomainModel) = viewModelScope.launch {
        changeFavoriteStatusUseCase(user).collect { result ->
            result.onLoading {
                _state.send(HomeState.Loading(it))
            }.onSuccess {
                getFavoriteUsers()
            }.onError {
                _state.send(HomeState.Error("Favoriye eklenemedi/çıkartılamadı", it.message))
            }
        }
    }

    private fun getFavoriteUsers() = viewModelScope.launch {
        getFavoriteUsersUseCase.invoke().collect{result->
            result.onSuccess {
                if(it.isNullOrEmpty())
                    return@onSuccess _state.send(HomeState.Empty)
                _state.send(HomeState.FavoriteUsersFound(it))
            }.onError {
                _state.send(HomeState.Error("Favori kullanıcılar çekilirken bir hata oluştu!", it.message))
            }.onLoading {
                _state.send(HomeState.Loading(it))
            }
        }
    }
}
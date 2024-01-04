package com.features.users.presentation.ui.users_page

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.core.common.base.BaseViewModel
import com.core.common.model.onError
import com.core.common.model.onLoading
import com.core.common.model.onSuccess
import com.features.users.domain.model.UserDomainModel
import com.features.users.domain.usecase.ChangeFavoriteStatusUseCase
import com.features.users.domain.usecase.GetUsersUseCase
import com.features.users.domain.usecase.SearchUserUseCase
import kotlinx.coroutines.launch

class UsersViewModel(
    private val getUsersUseCase : GetUsersUseCase,
    private val changeFavoriteStatusUseCase: ChangeFavoriteStatusUseCase,
    private val searchUserUseCase: SearchUserUseCase
) : BaseViewModel<UsersState, UsersIntent>() {

    private var users = mutableListOf<UserDomainModel>()

    override suspend fun handleIntent(intent: UsersIntent) {
        when(intent){
            is UsersIntent.GetUsers -> getUsers()
            is UsersIntent.SearchUser -> intent.username?.let { searchUser(it) }
            is UsersIntent.GoToUserDetailFragment -> _state.send(UsersState.NavigationToUserDetail(intent.user))
            is UsersIntent.MarkUserAsFavorite -> changeFavoriteFlag(intent.user)
            is UsersIntent.EmptySearch -> UsersState.Empty
        }
    }

    private fun getUsers() = viewModelScope.launch {
        getUsersUseCase.invoke().collect{ result->
            result.onSuccess {
                users = it?.toMutableList() ?: mutableListOf()
                _state.send(UsersState.UsersFound(it))
            }.onError {
                _state.send(UsersState.Error("Kullanıcılar alınırken bir hata oluştu", it.message))
            }.onLoading {
                _state.send(UsersState.Loading(it))
            }
        }
    }

    private fun changeFavoriteFlag(user: UserDomainModel) = viewModelScope.launch {
         changeFavoriteStatusUseCase.invoke(user).collect{ result ->
             result.onSuccess {
                 Log.i(TAG, "changeFavoriteFlag: Success ${user.isFavorite} - ${user.username}")
                 val newList = users.map {
                     if(user.username == it.username)
                         it.copy(isFavorite = !it.isFavorite)
                     else
                         it
                 }
                 users = newList.toMutableList()
                 Log.i(TAG, "changeFavoriteFlag: Success ${users.find { it.username == user.username }?.isFavorite}")
                 _state.send(UsersState.UserChangedFavorite(users))
             }.onError {
                 _state.send(UsersState.Error("favori işleminde hata oluitur hata:", it.localizedMessage))
             }.onLoading {
                 Log.i(TAG, "changeFavoriteFlag: Loading")
                 _state.send(UsersState.Loading(it))
             }
         }
    }

    private fun searchUser(username: String) = viewModelScope.launch {
        searchUserUseCase(username).collect{ result->
            result.onSuccess {users->
                if (users.isNullOrEmpty())
                    return@onSuccess _state.send(UsersState.Empty)
                _state.send(UsersState.UsersFound(users))
            }.onLoading {
                _state.send(UsersState.Loading(it))
            }.onError {
                _state.send(UsersState.Error("Kullanıcılar aranırken bir hata oluştu",it.localizedMessage))
            }
        }
    }
}
package com.features.users.presentation.ui.user_detail_page

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.core.common.base.BaseViewModel
import com.core.common.model.onError
import com.core.common.model.onLoading
import com.core.common.model.onSuccess
import com.features.users.domain.model.UserDetailDomainModel
import com.features.users.domain.model.UserDomainModel
import com.features.users.domain.usecase.ChangeFavoriteStatusUseCase
import com.features.users.domain.usecase.GetUserDetailUseCase
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class UserDetailViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val changeFavoriteStatusUseCase: ChangeFavoriteStatusUseCase
) : BaseViewModel<UserDetailState, UserDetailIntent>() {

    private var user: UserDetailDomainModel? = null
    override suspend fun handleIntent(intent: UserDetailIntent) {
        when (intent) {
            is UserDetailIntent.GetUserDetail -> getUserDetail(intent.username)
            is UserDetailIntent.ChangeFavoriteStatus -> changeFavoriteStatus(user)
        }
    }



    private fun changeFavoriteStatus(user: UserDetailDomainModel?) = viewModelScope.launch{
        user?.let {
            changeFavoriteStatusUseCase.invoke(user).collect{ result->
                result.onSuccess {
                    _state.send(UserDetailState.FavoriteStatusHasChanged(!user.isFavorite))
                }.onLoading {
                    _state.send(UserDetailState.Loading(it))
                }.onError {
                    _state.send(UserDetailState.Error("Favori ekleme/çıkarma işlemi yapılamıyor", it.localizedMessage))
                }
            }
        }
    }

    private fun getUserDetail(username: String) = viewModelScope.launch {
        getUserDetailUseCase(username).collect { result ->
            result.onSuccess {
                Log.i(TAG, "getUserDetail: 2")
                if (it == null)
                    return@onSuccess _state.send(UserDetailState.Error("Kullancı bulunamadı!", null))
                user = it
                _state.send(UserDetailState.UserDetailHasGot(it))
            }.onError {
                Log.i(TAG, "getUserDetail: 3 hata: $it")
                _state.send(UserDetailState.Error("Kullanıcı bilgileri alınırken bir hata oluştu", it.message))
            }.onLoading {
                Log.i(TAG, "getUserDetail: 4")
                _state.send(UserDetailState.Loading(it))
            }
        }
    }

}
package com.features.users.presentation.ui.user_detail_page

import com.core.common.base.BaseState
import com.features.users.domain.model.UserDetailDomainModel

sealed class UserDetailState : BaseState() {
    data class Loading(val isEnable: Boolean) : UserDetailState()
    data class Error(val message: String, val detail: String?) : UserDetailState()
    data class UserDetailHasGot(val user: UserDetailDomainModel) : UserDetailState()
    data class FavoriteStatusHasChanged(val isFavorite: Boolean) : UserDetailState()
}
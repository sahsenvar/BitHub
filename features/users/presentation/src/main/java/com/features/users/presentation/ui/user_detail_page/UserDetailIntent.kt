package com.features.users.presentation.ui.user_detail_page

import com.core.common.base.BaseIntent

sealed class UserDetailIntent : BaseIntent() {
    data class GetUserDetail(val username: String) : UserDetailIntent()
    data object ChangeFavoriteStatus : UserDetailIntent()

}
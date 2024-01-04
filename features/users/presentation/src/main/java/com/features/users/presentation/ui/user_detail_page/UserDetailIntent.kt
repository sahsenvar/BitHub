package com.features.users.presentation.ui.user_detail_page

import com.core.common.base.BaseIntent
import com.features.users.domain.model.UserDetailDomainModel
import com.features.users.domain.model.UserDomainModel

sealed class UserDetailIntent : BaseIntent() {
    data class GetUserDetail(val username: String) : UserDetailIntent()
    data object ChangeFavoriteStatus : UserDetailIntent()

}
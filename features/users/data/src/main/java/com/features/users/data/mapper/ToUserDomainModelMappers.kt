package com.features.users.data.mapper

import com.core.api.local.entity.FavoriteUserEntity
import com.core.api.remote.model.UserDataModel
import com.features.users.domain.model.UserDetailDomainModel
import com.features.users.domain.model.UserDomainModel

fun UserDataModel.toUserDomainModel(isFavorite: Boolean = false) = UserDomainModel(
    username = login,
    avatarUrl = avatarUrl ?: "",
    type = type,
    isFavorite = isFavorite
)
fun FavoriteUserEntity.toUserDomainModel(isFavorite: Boolean = false) = UserDomainModel(
    username = username,
    avatarUrl = avatarUrl ?: "",
    type = type,
    isFavorite = isFavorite
)

fun UserDetailDomainModel.toUserDomainModel(isFavorite: Boolean = false) = UserDomainModel(
    username = username,
    avatarUrl = avatarUrl,
    type = this.type,
    isFavorite = isFavorite
)
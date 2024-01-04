package com.features.users.data.mapper

import com.core.api.local.entity.FavoriteUserEntity
import com.features.users.domain.model.UserDetailDomainModel
import com.features.users.domain.model.UserDomainModel

fun UserDomainModel.toFavoriteUserEntity() = FavoriteUserEntity(
    username = username,
    avatarUrl = avatarUrl,
    type = type
)

fun UserDetailDomainModel.toFavoriteUserEntity() = FavoriteUserEntity(
    username = username,
    avatarUrl = avatarUrl,
    type = type
)
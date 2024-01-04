package com.features.users.data.mapper

import com.core.api.remote.model.UserDetailDataModel
import com.features.users.domain.model.UserDetailDomainModel

fun UserDetailDataModel.toUserDetailDomainModel(isFavorite: Boolean = false)= UserDetailDomainModel(
    username = login,
    avatarUrl = avatarUrl ?: "",
    htmlUrl = htmlUrl ?: "",
    type = type,
    name = name ?: "(no name)",
    company = company ?: "no company)",
    location = location ?: "(no location)",
    email = email ?: "(no email)",
    bio = bio ?: "(no bio)",
    twitterUsername = "",
    publicRepoCount = publicRepos ?: 0,
    publicGistCount = publicGists ?: 0,
    followerCount = followers ?: 0,
    followingCount = following ?: 0,
    createdAt = createdAt,
    updatedAt = updatedAt,
    isFavorite = isFavorite
)



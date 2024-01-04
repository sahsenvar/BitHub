package com.core.api.local.datasource

import com.core.api.local.entity.FavoriteUserEntity

interface UsersLocalDataSource {

    suspend fun getFavoriteUsers(): List<FavoriteUserEntity>

    suspend fun addFavoriteUser(user: FavoriteUserEntity)

    suspend fun removeFavoriteUser(user: FavoriteUserEntity)

    suspend fun getFavoriteUserByUsername(username: String): FavoriteUserEntity?

}
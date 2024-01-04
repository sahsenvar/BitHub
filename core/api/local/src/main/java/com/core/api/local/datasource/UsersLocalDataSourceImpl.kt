package com.core.api.local.datasource

import com.core.api.local.dao.UsersDao
import com.core.api.local.entity.FavoriteUserEntity

class UsersLocalDataSourceImpl(
    private val usersDao: UsersDao
): UsersLocalDataSource {
    override suspend fun getFavoriteUsers(): List<FavoriteUserEntity> {
        return usersDao.getFavoriteUsers()
    }

    override suspend fun addFavoriteUser(user: FavoriteUserEntity) {
        return usersDao.addFavoriteUser(user)
    }

    override suspend fun removeFavoriteUser(user: FavoriteUserEntity) {
        return usersDao.removeFavoriteUser(user)
    }

    override suspend fun getFavoriteUserByUsername(username: String): FavoriteUserEntity? {
        return usersDao.getFavoriteUserByUsername(username)
    }

}
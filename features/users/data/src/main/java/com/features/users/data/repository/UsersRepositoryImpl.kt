package com.features.users.data.repository

import com.core.api.local.datasource.UsersLocalDataSource
import com.core.api.remote.datasource.UsersDataSource
import com.features.users.data.mapper.toFavoriteUserEntity
import com.features.users.data.mapper.toUserDetailDomainModel
import com.features.users.data.mapper.toUserDomainModel
import com.features.users.domain.model.UserDetailDomainModel
import com.features.users.domain.model.UserDomainModel
import com.features.users.domain.repository.UsersRepository

class UsersRepositoryImpl(
    private val usersDataRemoteSource: UsersDataSource,
    private val usersLocalDataSource: UsersLocalDataSource
) : UsersRepository {
    override suspend fun getUsers(): List<UserDomainModel> {
        val favoriteUsers = usersLocalDataSource.getFavoriteUsers()
        return usersDataRemoteSource.getUsers().map { user ->
            user.toUserDomainModel(favoriteUsers.find { it.username == user.login } != null)
        }
    }
    override suspend fun searchUser(username: String): List<UserDomainModel> {
        val favoriteUsers = usersLocalDataSource.getFavoriteUsers()
        return usersDataRemoteSource.searchUser(username).items .map { user ->
            user.toUserDomainModel(favoriteUsers.find { it.username == user.login } != null)
        }
    }

    override suspend fun addFavoriteUser(user: UserDomainModel) {
        usersLocalDataSource.addFavoriteUser(user.toFavoriteUserEntity())
    }

    override suspend fun addFavoriteUser(user: UserDetailDomainModel) {
        usersLocalDataSource.addFavoriteUser(user.toFavoriteUserEntity())
    }

    override suspend fun removeFavoriteUser(user: UserDomainModel) {
        usersLocalDataSource.removeFavoriteUser(user.toFavoriteUserEntity())
    }

    override suspend fun removeFavoriteUser(user: UserDetailDomainModel) {
        usersLocalDataSource.removeFavoriteUser(user.toFavoriteUserEntity())
    }

    override suspend fun getFavoriteUsers(): List<UserDomainModel> {
        return usersLocalDataSource.getFavoriteUsers().map { it.toUserDomainModel(true) }
    }

    override suspend fun getUserDetail(username: String): UserDetailDomainModel {
        val isFavorite = usersLocalDataSource.getFavoriteUserByUsername(username) != null
        return usersDataRemoteSource.getUserDetail(username).toUserDetailDomainModel(isFavorite)
    }
}
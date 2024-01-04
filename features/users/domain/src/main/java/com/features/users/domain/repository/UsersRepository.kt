package com.features.users.domain.repository

import com.features.users.domain.model.UserDetailDomainModel
import com.features.users.domain.model.UserDomainModel

interface UsersRepository {
    suspend fun getUsers(): List<UserDomainModel>
    suspend fun addFavoriteUser(user: UserDomainModel)
    suspend fun removeFavoriteUser(user: UserDomainModel)
    suspend fun searchUser(username: String): List<UserDomainModel>
    suspend fun addFavoriteUser(user: UserDetailDomainModel)
    suspend fun removeFavoriteUser(user: UserDetailDomainModel)
    suspend fun getFavoriteUsers(): List<UserDomainModel>
    suspend fun getUserDetail(username: String): UserDetailDomainModel

}
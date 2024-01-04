package com.core.api.remote.datasource

import com.core.api.remote.model.UserDataModel
import com.core.api.remote.model.UserDetailDataModel
import com.core.api.remote.model.UserSearchResultDataModel
import com.core.api.remote.service.UserService

class UsersDataSourceImpl(
    private val userService: UserService
) : UsersDataSource {
    override suspend fun getUsers(): List<UserDataModel> {
        return userService.getUsers()
    }

    override suspend fun searchUser(username: String): UserSearchResultDataModel {
        return userService.searchUser(username)
    }

    override suspend fun getUserDetail(username: String): UserDetailDataModel {
        return userService.getUserDetail(username)
    }

}
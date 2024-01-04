package com.core.api.remote.datasource

import com.core.api.remote.model.UserDataModel
import com.core.api.remote.model.UserDetailDataModel
import com.core.api.remote.model.UserSearchResultDataModel

interface UsersDataSource {
    suspend fun getUsers(): List<UserDataModel>
    suspend fun searchUser(username: String): UserSearchResultDataModel
    suspend fun getUserDetail(username: String): UserDetailDataModel
}
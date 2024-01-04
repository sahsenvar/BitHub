package com.core.api.remote.service

import com.core.api.remote.model.UserDataModel
import com.core.api.remote.model.UserDetailDataModel
import com.core.api.remote.model.UserSearchResultDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<UserDataModel>
    @GET("search/users")
    suspend fun searchUser(@Query("q") username: String): UserSearchResultDataModel
    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String) : UserDetailDataModel
}
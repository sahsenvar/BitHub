package com.features.login_register.data.service

import com.features.login_register.domain.model.UserAuthDomainModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAuthService {
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getUserAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String
    ): UserAuthDomainModel

}
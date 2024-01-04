package com.features.login_register.data.repository

import com.features.login_register.data.service.UserAuthService
import com.features.login_register.domain.model.UserAuthDomainModel
import com.features.login_register.domain.repository.UserAuthRepository

class UserAuthRepositoryImpl(
    private val userAuthService: UserAuthService
) : UserAuthRepository {
    override suspend fun getUserAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String
    ): UserAuthDomainModel {
        return userAuthService.getUserAccessToken(clientId, clientSecret, code, redirectUri)
    }
}
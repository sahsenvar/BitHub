package com.features.login_register.domain.repository

import com.features.login_register.domain.model.UserAuthDomainModel

interface UserAuthRepository {
    suspend fun getUserAccessToken(
        clientId: String,
        clientSecret: String,
        code: String,
        redirectUri: String
    ) : UserAuthDomainModel
}
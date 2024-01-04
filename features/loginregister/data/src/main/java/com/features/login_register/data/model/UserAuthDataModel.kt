package com.features.login_register.data.model

import kotlinx.serialization.SerialName

data class UserAuthDataModel(
    @SerialName("access_token") val accessToken: String?,
    @SerialName("expires_in") val expiresIn: Int?,
    @SerialName("refresh_token") val refreshToken: String?,
    @SerialName("refresh_token_expires_in") val refreshTokenExpiresIn: Int?,
    @SerialName("scope") val scope: String?,
    @SerialName("token_type") val tokenType: String?
)

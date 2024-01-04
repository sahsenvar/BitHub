package com.features.login_register.domain.usecase

import BuildConfig
import com.core.common.base.BaseUseCase
import com.features.login_register.domain.repository.UserAuthRepository

class GetUserAccessTokenUseCase(
    private var userAuthRepository: UserAuthRepository
) : BaseUseCase() {
    operator fun invoke(query: String) = runUseCase {
        userAuthRepository.getUserAccessToken(
            clientId = BuildConfig.GitHubAuthApp.clientId,
            clientSecret = BuildConfig.GitHubAuthApp.clientSecret,
            code = query,
            redirectUri = BuildConfig.GitHubAuthApp.loginCallBackUrl
        )
    }
}
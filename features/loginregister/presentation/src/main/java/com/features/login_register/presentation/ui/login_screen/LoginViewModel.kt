package com.features.login_register.presentation.ui.login_screen

import PreferenceHelper
import androidx.lifecycle.viewModelScope
import com.core.common.base.BaseViewModel
import com.core.common.model.onError
import com.core.common.model.onLoading
import com.core.common.model.onSuccess
import com.features.login_register.domain.usecase.GetUserAccessTokenUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val getUserAccessTokenUseCase: GetUserAccessTokenUseCase,
    private val preferenceHelper: PreferenceHelper
) : BaseViewModel<LoginState, LoginIntent>() {
    override suspend fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.OpenToLoginWebView -> {}

            is LoginIntent.DirectToHomePage -> {
                _state.send(LoginState.NavigationToHomePage)
            }

            is LoginIntent.GetAccessToken -> {
                if (intent.codeQuery.isNullOrBlank())
                    _state.send(LoginState.Exception("response code boş geldi", null ))
                else
                    getAccessToken(intent.codeQuery)
            }
        }
    }


    private fun getAccessToken(codeQuery: String) = viewModelScope.launch {
        getUserAccessTokenUseCase.invoke(query = codeQuery).collect{ result ->
            result.onSuccess {
                if (it?.refreshToken.isNullOrBlank() && it?.accessToken.isNullOrBlank())
                    return@onSuccess _state.send(LoginState.Exception("access token boş geldi!"))
                preferenceHelper.upsertUserToken(it!!.accessToken!!)
                _state.send(LoginState.NavigationToHomePage)
            }.onLoading {
                _state.send(LoginState.Loading(it))
            }.onError {
                _state.send(LoginState.Exception("Access token alınamadı", it.localizedMessage))
            }
        }
    }
}
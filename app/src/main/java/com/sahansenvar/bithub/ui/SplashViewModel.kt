package com.sahansenvar.bithub.ui

import PreferenceHelper
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.core.common.base.BaseViewModel
import com.core.common.extentions.onError
import com.core.common.extentions.onFound
import kotlinx.coroutines.launch

class SplashViewModel(
    private val preferenceHelper: PreferenceHelper
) : BaseViewModel<SplashState, SplashIntent>() {

    override suspend fun handleIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.CheckUserLoginInfo -> checkUserLoginStatus()
        }
    }


    private fun checkUserLoginStatus() {
        viewModelScope.launch {
            preferenceHelper.findUserToken().onFound { accessToken ->
                if (accessToken.isEmpty()){
                    Log.i(TAG, "checkUserLoginStatus: accessToken bulunamadı Login'e yönelndiriliyor")
                    _state.send(SplashState.UserNotLoggedIn)
                }
                else{
                    Log.i(TAG, "checkUserLoginStatus: accessToken bulundu: $accessToken. Home' gidiliyor")
                    _state.send(SplashState.UserLoggedIn)
                }
            }.onError {
                Log.i(TAG, "checkUserLoginStatus: accessToken bulunamadı Login'e yönelndiriliyor")
                _state.send(SplashState.UserNotLoggedIn)
            }
        }
    }

}
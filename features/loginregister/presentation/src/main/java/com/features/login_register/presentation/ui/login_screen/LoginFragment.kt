package com.features.login_register.presentation.ui.login_screen

import BuildConfig
import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.base.Toolbar
import com.core.common.constant.InterModular
import com.features.loginregister.ui.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginState, LoginIntent, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModel()
    override val toolbar = Toolbar("Login")

    override fun onCreationFinished() {
        configureWebView()

    }

    override fun initListeners() {

    }

    override suspend fun observeState(state: LoginState) {
        when (state) {
            is LoginState.Loading -> {
                setLoadingVisibility(state.enable)
            }

            is LoginState.NavigationToHomePage -> {
                Log.e(TAG, "İşte gidiyoruz!!!", )
                findNavController().navigate(InterModular.toHome())
            }

            is LoginState.Exception -> {
                Log.e(TAG, "loginstate: exception: ${state.message}", )
                showErrorDialog(state.message, state.detail)
            }
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebView() {
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.clearCache(true)
        binding.webview.clearHistory()
        binding.webview.clearFormData()
        binding.webview.clearSslPreferences()
        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.startsWith(BuildConfig.GitHubAuthApp.loginCallBackUrl)) {
                    val responseCode = url.toUri().getQueryParameter("code")
                    viewModel.sendIntent(LoginIntent.GetAccessToken(responseCode))
                    return true
                }
                return false
            }
        }
        binding.webview.loadUrl(BuildConfig.GitHubAuthApp.loginUrl)
    }

}
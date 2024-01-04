package com.sahansenvar.bithub.ui

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.sahansenvar.bithub.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashState, SplashIntent, SplashViewModel>() {
    override val viewModel: SplashViewModel by viewModel()
    private val directions by lazy { SplashFragmentDirections }
    override fun onCreationFinished() {
        Log.i(TAG, "woke up")
        viewModel.sendIntent(SplashIntent.CheckUserLoginInfo)
    }

    override fun initListeners() {

    }


    override suspend fun observeState(state: SplashState) {
        when (state) {
            is SplashState.Loading -> {
                Log.e(TAG, "loginstate: exception", )
                setLoadingVisibility(state.isEnable)
            }
            is SplashState.UserLoggedIn -> {
                findNavController().navigate(directions.toHomeFragment())
            }
            is SplashState.UserNotLoggedIn -> {
                findNavController().navigate(directions.toLoginRegisterFragment())
            }
        }

    }

}
package com.features.login_register.presentation.ui.login_register_screen

import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.base.Toolbar
import com.features.loginregister.ui.databinding.FragmentLoginRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginRegisterFragment : BaseFragment<FragmentLoginRegisterBinding, LoginRegisterState, LoginRegisterIntent, LoginRegisterViewModel>() {
    override val viewModel: LoginRegisterViewModel by viewModel()
    override val toolbar = Toolbar("Login Or Register")
    private val directions by lazy { LoginRegisterFragmentDirections }
    override fun onCreationFinished() {

    }

    override fun initListeners() {
        binding.authViaGithub.setOnClickListener {
            viewModel.sendIntent(LoginRegisterIntent.DirectToLoginPage)
        }
    }

    override suspend fun observeState(state: LoginRegisterState) {
        when (state) {
            LoginRegisterState.NavigationToLoginPage -> {
                findNavController().navigate(directions.toLoginFragment())
            }

            LoginRegisterState.NavigationToRegisterPage -> {
                findNavController().navigate(directions.toRegisterFragment())
            }
        }
    }


}
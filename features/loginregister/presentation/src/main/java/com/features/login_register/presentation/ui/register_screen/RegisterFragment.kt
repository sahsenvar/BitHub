package com.features.login_register.presentation.ui.register_screen

import com.core.common.base.BaseFragment
import com.core.common.base.Toolbar
import com.features.loginregister.ui.databinding.FragmentRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterState, RegisterIntent, RegisterViewModel>() {
    override val viewModel: RegisterViewModel by viewModel()
    override val toolbar = Toolbar("Register")
    override fun onCreationFinished() {
    }

    override fun initListeners() {
    }
    override suspend fun observeState(state: RegisterState) {
    }








}
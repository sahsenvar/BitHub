package com.features.login_register.presentation.ui.login_resgister_screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.features.loginregister.ui.R

class LoginRegisterFragment : Fragment() {

    companion object {
        fun newInstance() = LoginRegisterFragment()
    }

    private lateinit var viewModel: LoginRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginRegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
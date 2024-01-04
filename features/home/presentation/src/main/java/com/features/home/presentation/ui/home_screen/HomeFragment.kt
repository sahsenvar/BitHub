package com.features.home.presentation.ui.home_screen

import android.view.View
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.base.Toolbar
import com.core.common.constant.InterModular
import com.features.home.presentation.ui.home_screen.adapter.UsersAdapter
import com.features.home.ui.databinding.FragmentHomeBinding
import com.features.users.domain.model.UserDomainModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeState, HomeIntent, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModel()
    override val toolbar = Toolbar("Home")
    override val bottomBarVisibility = true
    private val usersAdapter by lazy { UsersAdapter(onUserSelected, onUserMarkedFavorite) }

    override fun onCreationFinished() {
        viewModel.sendIntent(HomeIntent.GetFavoriteUsers)
        binding.rvFavoriteUsers.adapter = usersAdapter
    }

    override fun initListeners() {

    }

    override suspend fun observeState(state: HomeState) {
        when (state) {
            is HomeState.Loading -> {
                setLoadingVisibility(state.isEnable)
            }

            is HomeState.NavigationToLogin -> {
                findNavController().navigate(InterModular.toLogin())
            }

            is HomeState.Error -> {
                showErrorDialog(state.message, state.detail)
            }

            is HomeState.FavoriteUsersFound -> {
                usersAdapter.submitList(state.users)
            }

            is HomeState.Empty -> {
                binding.tvNothingToSeeHere.visibility = View.VISIBLE
                binding.rvFavoriteUsers.visibility = View.GONE
                binding.tvFavorites.visibility = View.GONE
            }

            is HomeState.NavigationToUserDetailFragment -> {
                findNavController().navigate(InterModular.toUserDetail(state.users.username))
            }
        }
    }

    private val onUserSelected: (user: UserDomainModel) -> Unit = {
        viewModel.sendIntent(HomeIntent.GoToUserDetailFragment(it))
    }

    private val onUserMarkedFavorite: (user: UserDomainModel) -> Unit = {
        viewModel.sendIntent(HomeIntent.ChangeFavoriteStatus(it))
    }


}
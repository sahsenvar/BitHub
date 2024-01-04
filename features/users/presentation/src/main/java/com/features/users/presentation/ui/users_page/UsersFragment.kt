package com.features.users.presentation.ui.users_page


import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.core.common.base.BaseFragment
import com.core.common.base.Toolbar
import com.core.common.enums.ToolbarType
import com.features.users.domain.model.UserDomainModel
import com.features.users.presentation.databinding.FragmentUsersBinding
import com.features.users.presentation.ui.users_page.adapter.UsersAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment<FragmentUsersBinding, UsersState, UsersIntent, UsersViewModel>() {
    override val viewModel: UsersViewModel by viewModel()
    override val toolbar = Toolbar("Users", ToolbarType.TitleSearch)
    override val bottomBarVisibility = true
    private val directions by lazy { UsersFragmentDirections }
    private val usersAdapter by lazy { UsersAdapter(onUserSelected, onUserMarkedFavorite) }
    override fun onCreationFinished() {
        viewModel.sendIntent(UsersIntent.GetUsers)
    }
    override suspend fun observeState(state: UsersState) {
        when (state) {
            is UsersState.Loading -> {
                setLoadingVisibility(state.isEnable)
            }
            is UsersState.Error -> {
                Log.e(TAG, "users state: exception: ${state.message}")
                showErrorDialog(state.message, state.detail)
            }
            is UsersState.UsersFound -> {
                if(state.users.isNullOrEmpty()){
                    binding.tvNothingToSeeHere.visibility = View.VISIBLE
                    binding.rvUsers.visibility = View.GONE
                }
                else{
                    binding.rvUsers.visibility = View.VISIBLE
                    binding.rvUsers.adapter = usersAdapter
                    usersAdapter.submitList(state.users)
                }
            }
            is UsersState.UserChangedFavorite ->{
                usersAdapter.submitList(state.users)
            }
            is UsersState.NavigationToUserDetail -> {
                findNavController().navigate(directions.toUserDetailFragment(state.user.username))
            }
            is UsersState.Empty ->{
                binding.rvUsers.visibility = View.GONE
                binding.tvNothingToSeeHere.visibility = View.VISIBLE
            }
        }
    }

    private val onUserSelected: (user: UserDomainModel) -> Unit = {
        viewModel.sendIntent(UsersIntent.GoToUserDetailFragment(it))
    }

    private val onUserMarkedFavorite: (user: UserDomainModel) -> Unit = {
        viewModel.sendIntent(UsersIntent.MarkUserAsFavorite(it))
    }
    override fun initListeners() {
        setToolbarSearchTrigger()
    }


    private fun setToolbarSearchTrigger(){
        var searchJob: Job? = null
        registerSearchQueryChangeListener(
            onQueryTextSubmit = {newInput->
                searchJob?.cancel()
                if (newInput.isNullOrBlank()){
                    viewModel.sendIntent(UsersIntent.EmptySearch)
                    return@registerSearchQueryChangeListener false
                }
                viewModel.sendIntent(UsersIntent.SearchUser(newInput))
                return@registerSearchQueryChangeListener true
            },
            onQueryTextChange = { newInput->
                searchJob?.cancel()
                if (newInput.isNullOrBlank()){
                    viewModel.sendIntent(UsersIntent.EmptySearch)
                    return@registerSearchQueryChangeListener false
                }
                searchJob = lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        delay(1500L)
                        viewModel.sendIntent(UsersIntent.SearchUser(newInput))
                    }
                }
                return@registerSearchQueryChangeListener true
            }
        )
    }


}
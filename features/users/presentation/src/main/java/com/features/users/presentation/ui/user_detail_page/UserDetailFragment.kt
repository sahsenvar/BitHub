package com.features.users.presentation.ui.user_detail_page

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import coil.load
import com.core.common.R
import com.core.common.base.BaseFragment
import com.core.common.base.Toolbar
import com.core.common.enums.ToolbarType
import com.features.users.presentation.databinding.FragmentUserDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailFragment :
    BaseFragment<FragmentUserDetailBinding, UserDetailState, UserDetailIntent, UserDetailViewModel>() {
    override val viewModel: UserDetailViewModel by viewModel()
    override val toolbar = Toolbar("User Detail")
    private val args: UserDetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onCreationFinished() {
        viewModel.sendIntent(UserDetailIntent.GetUserDetail(args.username))
    }

    override fun initListeners() {
        binding.ivFavorite.setOnClickListener {
            viewModel.sendIntent(UserDetailIntent.ChangeFavoriteStatus)
        }
    }

    override suspend fun observeState(state: UserDetailState) {
        when (state) {
            is UserDetailState.Error -> {
                showErrorDialog(state.message, state.detail)
            }

            is UserDetailState.Loading -> {
                setLoadingVisibility(state.isEnable)
            }

            is UserDetailState.UserDetailHasGot -> {
                with(state.user) {
                    binding.apply {
                        tvUsername.text = username
                        ivAvatar.load(avatarUrl) { placeholder(R.drawable.github_mark) }
                        tvType.text = type
                        tvBio.text = bio
                        tvFollowerCount.text = followerCount.toString()
                        tvFollowingCount.text = followingCount.toString()
                        tvRepoCount.text = publicRepoCount.toString()
                        val icon = if (isFavorite)
                            R.drawable.baseline_favorite_filled
                        else
                            R.drawable.baseline_favorite
                        ivFavorite.setImageResource(icon)
                        btnGoProfileLink.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse(htmlUrl)
                            }
                            startActivity(intent)
                        }
                    }

                }
            }

            is UserDetailState.FavoriteStatusHasChanged -> {
               val icon = if(state.isFavorite) R.drawable.baseline_favorite_filled
                else R.drawable.baseline_favorite
                binding.ivFavorite.setImageResource(icon)
            }
        }

    }


}
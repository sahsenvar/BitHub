package com.features.home.presentation.ui.home_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.features.home.ui.databinding.ItemFavoriteUserBinding
import com.features.users.domain.model.UserDomainModel

class UsersAdapter(
    private val onUserSelected: (user: UserDomainModel) -> Unit,
    private val onUserMarkedFavorite: (user: UserDomainModel) -> Unit
) : ListAdapter<UserDomainModel, UsersAdapter.UsersViewHolder>(differ) {

    companion object {
        val differ = object: DiffUtil.ItemCallback<UserDomainModel>() {
            override fun areItemsTheSame(
                oldItem: UserDomainModel,
                newItem: UserDomainModel
            ): Boolean {
                return oldItem.username == newItem.username
            }

            override fun areContentsTheSame(
                oldItem: UserDomainModel,
                newItem: UserDomainModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class UsersViewHolder(private val binding: ItemFavoriteUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: UserDomainModel) {
            with(item) {
                binding.ivAvatar.load(avatarUrl)
                binding.tvUsername.text = username
                setFavoriteIcon(item.isFavorite)
            }
            binding.root.setOnClickListener {
                onUserSelected.invoke(item)
            }
            binding.ivFavorite.setOnClickListener {
                onUserMarkedFavorite(item)
            }
        }

        fun setFavoriteIcon(isFavorite: Boolean) {
            val icon = if (isFavorite)
                com.core.common.R.drawable.baseline_favorite_filled
            else
                com.core.common.R.drawable.baseline_favorite
            binding.ivFavorite.setImageResource(icon)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersAdapter.UsersViewHolder {
        val itemBinding = ItemFavoriteUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UsersViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UsersAdapter.UsersViewHolder, position: Int) {
        holder.bindItems(currentList[position])
    }


}
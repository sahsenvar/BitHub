package com.features.users.presentation.ui.users_page.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.features.users.domain.model.UserDomainModel
import com.features.users.presentation.databinding.ItemUsersBinding

class UsersAdapter(
    private val onUserSelected: (user: UserDomainModel) -> Unit,
    private val onUserMarkedFavorite: (user: UserDomainModel) -> Unit
) : ListAdapter<UserDomainModel, UsersAdapter.UsersViewHolder>(differ) {

    companion object {
        private const val FAVORITE_PAYLOAD = 0
        val differ = object : DiffUtil.ItemCallback<UserDomainModel>() {
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

            override fun getChangePayload(
                oldItem: UserDomainModel,
                newItem: UserDomainModel
            ): Any? {
                return if (oldItem.isFavorite != newItem.isFavorite)
                    FAVORITE_PAYLOAD
                else
                    super.getChangePayload(oldItem, newItem)
            }
        }
    }

    inner class UsersViewHolder(private val binding: ItemUsersBinding) :
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
    ): UsersViewHolder {
        val itemBinding = ItemUsersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UsersViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bindItems(currentList[position])
    }

    override fun onBindViewHolder(
        holder: UsersViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            if (payloads[0] == FAVORITE_PAYLOAD) {
                holder.setFavoriteIcon(currentList[position].isFavorite)
            } else {
                super.onBindViewHolder(holder, position, payloads)
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

}
package com.core.api.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_users")
data class FavoriteUserEntity(
    @PrimaryKey
    val username          : String,
    val avatarUrl         : String?,
    val type              : String
)
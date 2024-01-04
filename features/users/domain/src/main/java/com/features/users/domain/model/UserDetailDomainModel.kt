package com.features.users.domain.model

data class UserDetailDomainModel(
    val username             : String,
    val avatarUrl         : String,
    val htmlUrl           : String,
    val type              : String,
    val name              : String,
    val company           : String,
    val location          : String,
    val email             : String,
    val bio               : String,
    val twitterUsername   : String,
    val publicRepoCount       : Int,
    val publicGistCount       : Int,
    val followerCount         : Int,
    val followingCount         : Int,
    val createdAt         : String,
    val updatedAt         : String,
    var isFavorite        : Boolean
)

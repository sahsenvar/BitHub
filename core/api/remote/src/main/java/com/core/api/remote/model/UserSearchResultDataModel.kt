package com.core.api.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class UserSearchResultDataModel(
    @SerialName("total_count") val totalCount: Int,
    @SerialName("incomplete_results") val incompleteResults: Boolean,
    @SerialName("items") val items: List<UserDataModel>
)

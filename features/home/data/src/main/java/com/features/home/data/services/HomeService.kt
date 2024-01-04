package com.features.home.data.services

import com.core.common.model.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface HomeService {

    @GET("user")
    suspend fun getCurrentUserUrl() : Flow<NetworkResult<String>>

}
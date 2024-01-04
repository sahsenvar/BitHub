package com.core.api.remote.di

import BuildConfig
import com.core.api.remote.constant.NetworkConstants
import com.core.api.remote.constant.NetworkConstants.OKHTTP_FOR_GENERAL
import com.core.api.remote.constant.NetworkConstants.RETROFIT_FOR_AUTH_CLIENT
import com.core.api.remote.constant.NetworkConstants.RETROFIT_FOR_GENERAL
import com.core.api.remote.datasource.UsersDataSource
import com.core.api.remote.datasource.UsersDataSourceImpl
import com.core.api.remote.interceptor.FlipperNetworkObject
import com.core.api.remote.interceptor.GitHubTokenInterceptor
import com.core.api.remote.interceptor.UserAuthInterceptor
import com.core.api.remote.service.UserService
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val remoteApiModule = module {

    factory<UserService> { createApiService(retrofit = get(named(RETROFIT_FOR_GENERAL))) }
    factory<UsersDataSource> { UsersDataSourceImpl(get()) }


    factory(named(OKHTTP_FOR_GENERAL)) {
        createOkHttpClient(interceptor = GitHubTokenInterceptor(get(), get()))
    }

    factory(named(RETROFIT_FOR_GENERAL)) {
        createRetrofit(get(named(OKHTTP_FOR_GENERAL)), BuildConfig.baseUrl)
    }

    factory(named(NetworkConstants.OKHTTP_FOR_AUTH_CLIENT)) {
        createOkHttpClient(interceptor = UserAuthInterceptor())
    }

    factory(named(RETROFIT_FOR_AUTH_CLIENT)) {
        createRetrofit(
            okHttpClient = get(named(NetworkConstants.OKHTTP_FOR_AUTH_CLIENT)),
            baseUrl = BuildConfig.GitHubAuthApp.baseUrl
        )
    }

}

private fun createHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.isDebuggable)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
}


fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(createHttpLoggingInterceptor())
        .addInterceptor(interceptor)
        .build()
}

fun appClient(okHttpClient: OkHttpClient): OkHttpClient.Builder {
    return if (FlipperNetworkObject.networkFlipperPlugin != null) {
        okHttpClient.newBuilder().addNetworkInterceptor(
            interceptor = FlipperOkhttpInterceptor(FlipperNetworkObject.networkFlipperPlugin)
        )
    } else {
        okHttpClient.newBuilder()
    }
}

private val jsonBuilder = Json {
    ignoreUnknownKeys = true
}

fun createRetrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String,
): Retrofit {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(appClient(okHttpClient).build())
        .addConverterFactory(jsonBuilder.asConverterFactory(contentType))
        .build()
}

inline fun <reified T> createApiService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}
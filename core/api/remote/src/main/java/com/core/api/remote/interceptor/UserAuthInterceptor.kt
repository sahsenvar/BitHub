package com.core.api.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class UserAuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Accept","application/json")
        return chain.proceed(builder.build())
    }

}
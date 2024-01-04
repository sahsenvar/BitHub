package com.core.api.remote.interceptor

import PreferenceHelper
import android.content.Context
import android.util.Log
import com.core.common.extentions.isNetworkAvailable
import com.core.common.extentions.onFound
import com.core.common.model.NetworkExceptions
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.koin.core.component.KoinComponent
import java.io.IOException
import java.net.SocketTimeoutException

class GitHubTokenInterceptor(
    private val preferenceHelper: PreferenceHelper,
    private val context: Context
) : Interceptor, KoinComponent {
    lateinit var accessToken: String
    init {
        runBlocking{
            preferenceHelper.findUserToken().onFound {
                accessToken = it
            }
        }

    }
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isNetworkAvailable()) {
            throw (NetworkExceptions.NetworkUnavailableException)
        }
        Log.i("Bithub", "intercept: accessToken: $accessToken")
        val builder = chain.request().newBuilder()
            .header("Accept", "application/vnd.github+json")
            .header("Authorization", "Bearer $accessToken")
            .header("X-GitHub-Api-Version", "2022-11-28")
            .build()
        return try {
            println("chain.proceed başladı")
            chain.proceed(builder)
        } catch (e: Exception) {
            Log.i("BitHub", "upps. hata döndü")
            getHandledResponse(chain.request(), e)
        }

    }

    private fun getHandledResponse(request: Request, e: Exception): Response {
        val message = when (e) {
            is SocketTimeoutException -> "Timout - please check your internet connection"
            is IOException -> "Server unreachable, Please try again later"
            else -> e.message.toString()
        }
        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_0)
            .code(999)
            .message(message)
            .body(e.toString().toResponseBody())
            .build()
    }


}
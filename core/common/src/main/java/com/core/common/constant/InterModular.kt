package com.core.common.constant

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest

object InterModular {
    val toHome = fun() = NavDeepLinkRequest.Builder.fromUri("bithub://home.feature.com".toUri()).build()
    val toLogin = fun() = NavDeepLinkRequest.Builder.fromUri("bithub://login.feature.com".toUri()).build()
    val toUserDetail = fun(username: String) = NavDeepLinkRequest.Builder
        .fromUri("bithub://userDetail.feature.com/data?username=$username".toUri()).build()

}
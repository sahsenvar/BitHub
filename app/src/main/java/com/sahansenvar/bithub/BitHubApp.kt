package com.sahansenvar.bithub

import BuildConfig
import android.app.Application
import com.core.api.local.di.localApiModule
import com.core.api.remote.di.remoteApiModule
import com.core.api.remote.interceptor.FlipperNetworkObject
import com.core.common.di.commonModule
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.features.home.data.di.homeDataLayerModule
import com.features.home.domain.di.homeDomainLayerModule
import com.features.home.presentation.di.homePresentationLayerModule
import com.features.login_register.data.di.loginRegisterDataLayerModule
import com.features.login_register.domain.di.loginRegisterDomainLayerModule
import com.features.login_register.presentation.di.loginRegisterPresentationLayerModule
import com.features.users.data.di.userDataLayerModule
import com.features.users.domain.di.usersDomainLayerModule
import com.features.users.presentation.di.usersPresentationLayerModule
import com.sahansenvar.bithub.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin

class BitHubApp : Application() {

    private val modules = listOf(
        appModule,
        commonModule,
        localApiModule,
        remoteApiModule,
        loginRegisterDataLayerModule,
        loginRegisterDomainLayerModule,
        loginRegisterPresentationLayerModule,
        homeDataLayerModule,
        homeDomainLayerModule,
        homePresentationLayerModule,
        userDataLayerModule,
        usersDomainLayerModule,
        usersPresentationLayerModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin()
        startFlipper()
    }


    private fun startKoin() = startKoin {
        androidLogger()
        androidContext(this@BitHubApp)
        androidLogger()
        GlobalContext.loadKoinModules(modules)
    }

    private fun startFlipper() {
        SoLoader.init(this, false)
        if (BuildConfig.isDebuggable && FlipperUtils.shouldEnableFlipper(this)) {
            val clint: FlipperClient = AndroidFlipperClient.getInstance(this)
            val networkFlipperPlugin = NetworkFlipperPlugin()
            FlipperNetworkObject.networkFlipperPlugin = networkFlipperPlugin
            clint.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            clint.addPlugin(SharedPreferencesFlipperPlugin(this, "BitHubFlipperPlugin"))
            clint.addPlugin(networkFlipperPlugin)
            clint.start()
        }
    }

}
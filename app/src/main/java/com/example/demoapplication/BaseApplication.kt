package com.example.demoapplication

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.example.demoapplication.util.ConnectivityObserver
import com.example.demoapplication.util.RuntimeLocaleChanger
import com.example.demoapplication.util.SharedPrefsUtils
import com.zeugmasolutions.localehelper.LocaleHelperApplicationDelegate
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        ConnectivityObserver.init(applicationContext)
        super.onCreate()
    }

    private val localeAppDelegate = LocaleHelperApplicationDelegate()

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localeAppDelegate.attachBaseContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeAppDelegate.onConfigurationChanged(this)
    }
}
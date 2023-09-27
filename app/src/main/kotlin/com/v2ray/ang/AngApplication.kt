package com.v2ray.ang

import android.content.res.Configuration
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@HiltAndroidApp
class AngApplication : MultiDexApplication() {
    companion object {
        const val PREF_LAST_VERSION = "pref_last_version"
    }

    var firstRun = false
        private set

    override fun onCreate() {
        super.onCreate()

//        LeakCanary.install(this)

        val defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        firstRun = defaultSharedPreferences.getInt(PREF_LAST_VERSION, 0) != BuildConfig.VERSION_CODE
        if (firstRun)
            defaultSharedPreferences.edit().putInt(PREF_LAST_VERSION, BuildConfig.VERSION_CODE).apply()

        //Logger.init().logLevel(if (BuildConfig.DEBUG) LogLevel.FULL else LogLevel.NONE)
        MMKV.initialize(this)

        setLocaleToRTL()
    }

    private fun setLocaleToRTL() {
        val locale = Locale("fa_IR")

        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        applicationContext.resources.updateConfiguration(
            config,
            applicationContext.resources.displayMetrics
        )
    }
}

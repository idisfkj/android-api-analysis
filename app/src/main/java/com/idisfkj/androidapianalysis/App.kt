package com.idisfkj.androidapianalysis

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import androidx.work.Configuration

/**
 * Created by idisfkj on 2019-08-01.
 * Email : idisfkj@gmail.com.
 */
class App : Application(), Configuration.Provider {

    override fun getWorkManagerConfiguration(): Configuration =
            Configuration.Builder()
                    .setMinimumLoggingLevel(Log.DEBUG)
                    .build()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
package com.idisfkj.androidapianalysis

import android.app.Application
import android.util.Log
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

}
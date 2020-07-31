package com.idisfkj.androidapianalysis.appsetup

import android.content.Context
import androidx.startup.Initializer
import com.idisfkj.androidapianalysis.utils.LogUtils

/**
 * Created by idisfkj on 2020/7/13.
 * Email: idisfkj@gmail.com.
 */
class AppSetupDependencies: Initializer<String> {
    override fun create(context: Context): String {
        LogUtils.d("AppSetupDependencies create")
        return "AppSetupDependencies Success"
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        LogUtils.d("AppSetupDependencies dependencies")
        return mutableListOf()
    }
}
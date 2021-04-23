package com.rousetime.trace_plugin.utils

/**
 * Created by idisfkj on 4/9/21.
 */
object LogUtils {

    private var isDebug = true
    private const val TAG = "TracePlugin"

    fun d(message: String) {
        if (isDebug) println("$TAG: $message")
    }

}
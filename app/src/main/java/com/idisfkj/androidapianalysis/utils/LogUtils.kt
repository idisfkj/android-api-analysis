package com.idisfkj.androidapianalysis.utils

import android.util.Log

object LogUtils {

    var DEBUG = true
    var TAG = "android-api-analysis"
    fun d(msg: String) {
        if (DEBUG) {
            Log.d(TAG, buildMessage(false, msg))
        }
    }

    fun dStack(formatStr: String, vararg values: Any) {
        if (DEBUG) {
            Log.d(TAG, buildMessage(true, String.format(formatStr, *values)))
        }
    }

    fun d(formatStr: String, vararg values: Any) {
        if (DEBUG) {
            Log.d(TAG, buildMessage(false, String.format(formatStr, *values)))
        }
    }

    fun i(msg: String) {
        if (DEBUG) {
            Log.i(TAG, buildMessage(false, msg))
        }
    }

    fun e(msg: String, error: Throwable) {
        if (DEBUG) {
            Log.e(TAG, buildMessage(false, msg), error)
        }
    }

    fun w(msg: String) {
        if (DEBUG) {
            Log.w(TAG, buildMessage(false, msg))
        }
    }

    private fun buildMessage(logStack: Boolean, msg: String): String {
        val caller = Throwable().fillInStackTrace().stackTrace[2]
        val stackTraceElements = Thread.currentThread().stackTrace
        val sb = StringBuilder()
        sb.append(caller.className)
                .append(".")
                .append(caller.methodName)
                .append("(): [")
                .append(caller.lineNumber)
                .append("]")
                .append(msg).append("\n")

        if (logStack) {
            for (ste in stackTraceElements) {
                sb.append(ste).append("\n")
            }
        }
        return sb.toString()
    }
}
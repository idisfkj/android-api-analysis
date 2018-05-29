package com.idisfkj.androidapianalysis

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by idisfkj on 2018/5/28.
 * Email : idisfkj@gmail.com.
 */
class ExecutorsHelper {

    var disIoExecutor: Executor = Executors.newSingleThreadExecutor()

    var mainExecutor: Executor = MainExecutor()

    companion object {
        class MainExecutor : Executor {
            private val mHandler by lazy { Handler(Looper.getMainLooper()) }
            override fun execute(command: Runnable?) {
                mHandler.post(command)
            }
        }
    }
}
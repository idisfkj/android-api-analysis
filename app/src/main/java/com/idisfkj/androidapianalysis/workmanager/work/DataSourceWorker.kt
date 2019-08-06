package com.idisfkj.androidapianalysis.workmanager.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.idisfkj.androidapianalysis.utils.LogUtils

/**
 * Created by idisfkj on 2019-08-05.
 * Email : idisfkj@gmail.com.
 */
class DataSourceWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        LogUtils.d("DataSourceWorker doWork")
        val result = todoWork()
        // if it is periodic work, workData will invalidate, because periodic work not in chain work.
//        return Result.success(workDataOf(Constants.KEY_DATA_SOURCE to result))
        return Result.success()
    }

    private fun todoWork(): String {
        // todo something
        return "working..."
    }

}
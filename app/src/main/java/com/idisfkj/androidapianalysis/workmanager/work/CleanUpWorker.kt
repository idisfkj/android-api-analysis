package com.idisfkj.androidapianalysis.workmanager.work

import android.content.Context
import android.text.TextUtils
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.idisfkj.androidapianalysis.workmanager.Constants
import java.io.File

/**
 * Created by idisfkj on 2019-08-05.
 * Email : idisfkj@gmail.com.
 */
class CleanUpWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val outputDir = File(applicationContext.filesDir, Constants.OUTPUT_PATH)
        if (outputDir.exists()) {
            val fileLists = outputDir.listFiles()
            for (file in fileLists) {
                val fileName = file.name
                if (!TextUtils.isEmpty(fileName) && fileName.endsWith(".png")) {
                    file.delete()
                }
            }
        }
        return Result.success()
    }
}
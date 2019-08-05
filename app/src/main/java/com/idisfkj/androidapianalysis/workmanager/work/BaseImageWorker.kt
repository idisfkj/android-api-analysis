package com.idisfkj.androidapianalysis.workmanager.work

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.idisfkj.androidapianalysis.utils.LogUtils
import com.idisfkj.androidapianalysis.workmanager.Constants
import java.io.File
import java.io.OutputStream
import java.util.*

/**
 * Created by idisfkj on 2019-08-01.
 * Email : idisfkj@gmail.com.
 */
abstract class BaseImageWorker(appContext: Context, params: WorkerParameters)
    : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val resId = inputData.getInt(Constants.KEY_IMAGE_RES_ID, -1)
        if (resId != -1) {
            val bitmap = BitmapFactory.decodeResource(applicationContext.resources, resId)
            val outputBitmap = apply(bitmap)
            val outputFileUri = writeToFile(outputBitmap)
            LogUtils.d("doWork %s", outputFileUri.toString())
            return Result.success(workDataOf(Constants.KEY_IMAGE_URI to outputFileUri.toString()))
        }
        return Result.failure()
    }

    private fun writeToFile(bitmap: Bitmap): Uri {
        val name = String.format("android-api-analysis-output-%s.png", UUID.randomUUID().toString())
        val outputDir = File(applicationContext.filesDir, Constants.OUTPUT_PATH)
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
        val outputFile = File(outputDir, name)
        var outputStream: OutputStream? = null
        try {
            outputStream = outputFile.outputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        } finally {
            outputStream?.close()
        }
        return Uri.fromFile(outputFile)
    }

    abstract fun apply(bitmap: Bitmap): Bitmap

}
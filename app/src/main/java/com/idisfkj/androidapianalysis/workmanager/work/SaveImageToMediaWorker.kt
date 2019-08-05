package com.idisfkj.androidapianalysis.workmanager.work

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.idisfkj.androidapianalysis.utils.LogUtils
import com.idisfkj.androidapianalysis.workmanager.Constants

/**
 * Created by idisfkj on 2019-08-02.
 * Email : idisfkj@gmail.com.
 */
class SaveImageToMediaWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val resolver = applicationContext.contentResolver
        val imageUri = inputData.getString(Constants.KEY_IMAGE_URI)
        val bitmap = BitmapFactory.decodeStream(resolver.openInputStream(Uri.parse(imageUri)))
        val imageUrl = MediaStore.Images.Media.insertImage(resolver, bitmap, "blur image", "")
        if (TextUtils.isEmpty(imageUrl)) {
            return Result.failure()
        }
        LogUtils.d("saveImageToMedia %s", imageUrl)
        return Result.success(workDataOf(Constants.KEY_SHOW_IMAGE_URI to imageUrl))
    }
}
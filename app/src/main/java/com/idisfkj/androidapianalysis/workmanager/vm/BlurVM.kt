package com.idisfkj.androidapianalysis.workmanager.vm

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.workmanager.Constants
import com.idisfkj.androidapianalysis.workmanager.work.BlurImageWorker
import com.idisfkj.androidapianalysis.workmanager.work.CleanUpWorker
import com.idisfkj.androidapianalysis.workmanager.work.SaveImageToMediaWorker

/**
 * Created by idisfkj on 2019-08-02.
 * Email : idisfkj@gmail.com.
 */
class BlurVM(application: Application) : AndroidViewModel(application) {

    private val mWorkManager: WorkManager = WorkManager.getInstance(application)

    internal val blurWorkInfo: LiveData<List<WorkInfo>>
        get() = mWorkManager.getWorkInfosByTagLiveData(Constants.TAG_BLUR_IMAGE)

    internal val saveWorkInfo: LiveData<List<WorkInfo>>
        get() = mWorkManager.getWorkInfosByTagLiveData(Constants.TAG_SAVE_IMAGE)

    val processEnable = MutableLiveData<Boolean>(true)

    val showImageEnable = MutableLiveData<Boolean>(false)

    val blurUri = MutableLiveData<Uri>(Uri.EMPTY)

    val showImage = MutableLiveData<Boolean>(false)

    fun processClick() {
        startWork()
    }

    fun showImageClick() {
        showImage.value = true
    }

    private fun startWork() {
        processEnable.value = false
        showImageEnable.value = false

        val cleanUpWork = OneTimeWorkRequestBuilder<CleanUpWorker>().build()
        val workContinuation = mWorkManager.beginUniqueWork(Constants.IMAGE_UNIQUE_WORK, ExistingWorkPolicy.REPLACE, cleanUpWork)

        val blurRequest = OneTimeWorkRequestBuilder<BlurImageWorker>()
                .setInputData(workDataOf(Constants.KEY_IMAGE_RES_ID to R.drawable.yaodaoji))
                .addTag(Constants.TAG_BLUR_IMAGE)
                .build()

//        workContinuation.enqueue()

        val saveRequest = OneTimeWorkRequestBuilder<SaveImageToMediaWorker>()
                .addTag(Constants.TAG_SAVE_IMAGE)
                .build()

        workContinuation.then(blurRequest)
                .then(saveRequest)
                .enqueue()
    }

    fun cancelWork() {
        mWorkManager.cancelUniqueWork(Constants.IMAGE_UNIQUE_WORK)
    }
}
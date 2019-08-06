package com.idisfkj.androidapianalysis.workmanager.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.idisfkj.androidapianalysis.workmanager.Constants
import com.idisfkj.androidapianalysis.workmanager.work.DataSourceWorker
import java.util.concurrent.TimeUnit

/**
 * Created by idisfkj on 2019-08-05.
 * Email : idisfkj@gmail.com.
 */
class OtherWorkerVM(application: Application) : AndroidViewModel(application) {

    private val mWorkManager: WorkManager = WorkManager.getInstance(application)

    private lateinit var mPeriodicRequest: WorkRequest

    internal val dataSourceInfo: MediatorLiveData<WorkInfo> = MediatorLiveData()

    val periodicEnable = MutableLiveData<Boolean>(true)


    val constraintsEnable = MutableLiveData<Boolean>(true)

    fun periodicClick() {
        periodicEnable.value = false
        constraintsEnable.value = false
        // at least 15 minutes
        mPeriodicRequest = PeriodicWorkRequestBuilder<DataSourceWorker>(15, TimeUnit.MINUTES)
                .addTag(Constants.TAG_DATA_SOURCE)
                .build()
        mWorkManager.enqueue(mPeriodicRequest)

        addSource()
    }

    fun constraintClick() {
        periodicEnable.value = false
        constraintsEnable.value = false
        val constraint = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        // at least 15 minutes
        mPeriodicRequest = PeriodicWorkRequestBuilder<DataSourceWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .addTag(Constants.TAG_DATA_SOURCE)
                .build()
        mWorkManager.enqueue(mPeriodicRequest)

        addSource()
    }

    private fun addSource() {
        val periodicWorkInfo = mWorkManager.getWorkInfoByIdLiveData(mPeriodicRequest.id)
        dataSourceInfo.addSource(periodicWorkInfo) {
            dataSourceInfo.value = it
        }
    }

    fun cancelClick() {
        periodicEnable.value = true
        constraintsEnable.value = true
        cancelWork()
    }

    fun cancelWork() {
        mWorkManager.cancelAllWorkByTag(Constants.TAG_DATA_SOURCE)
    }
}
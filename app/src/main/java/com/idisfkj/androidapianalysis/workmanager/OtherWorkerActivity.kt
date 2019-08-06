package com.idisfkj.androidapianalysis.workmanager

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.WorkInfo
import com.idisfkj.androidapianalysis.base.BaseDevActivity
import com.idisfkj.androidapianalysis.utils.LogUtils
import com.idisfkj.androidapianalysis.workmanager.vm.OtherWorkerVM

/**
 * Created by idisfkj on 2019-08-05.
 * Email : idisfkj@gmail.com.
 */
class OtherWorkerActivity : BaseDevActivity<com.idisfkj.androidapianalysis.databinding.ActivityOtherWorkerBinding, OtherWorkerVM>() {

    override fun getViewModelClass(): Class<OtherWorkerVM> = OtherWorkerVM::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addObserver()
    }

    private fun addObserver() {
        vm.dataSourceInfo.observe(this, Observer {
            if (it == null) return@Observer
            with(it) {
                if (state == WorkInfo.State.ENQUEUED) {
                    val result = outputData.getString(Constants.KEY_DATA_SOURCE)
                    LogUtils.d("result %s", result + "")
                    if (!TextUtils.isEmpty(result)) {
                        Toast.makeText(this@OtherWorkerActivity, result, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.cancelWork()
    }
}
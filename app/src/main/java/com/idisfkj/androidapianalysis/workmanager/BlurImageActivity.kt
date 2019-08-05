package com.idisfkj.androidapianalysis.workmanager

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import com.idisfkj.androidapianalysis.base.BaseDevActivity
import com.idisfkj.androidapianalysis.databinding.ActivityBlurImageBinding
import com.idisfkj.androidapianalysis.workmanager.vm.BlurVM

/**
 * Created by idisfkj on 2019-08-01.
 * Email : idisfkj@gmail.com.
 */
class BlurImageActivity : BaseDevActivity<ActivityBlurImageBinding, BlurVM>() {

    private var saveImageUri: String? = null

    override fun getViewModelClass(): Class<BlurVM> = BlurVM::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addObserver()
    }

    private fun addObserver() {
        vm.blurWorkInfo.observe(this, Observer {
            if (it == null || it.isEmpty()) return@Observer
            with(it[0]) {
                if (!state.isFinished) {
                    vm.processEnable.value = false
                } else {
                    vm.processEnable.value = true
                    val uri = outputData.getString(Constants.KEY_IMAGE_URI)
                    if (!TextUtils.isEmpty(uri)) {
                        vm.blurUri.value = Uri.parse(uri)
                    }
                }
            }
        })

        vm.saveWorkInfo.observe(this, Observer {
            saveImageUri = ""
            if (it == null || it.isEmpty()) return@Observer
            with(it[0]) {
                saveImageUri = outputData.getString(Constants.KEY_SHOW_IMAGE_URI)
                vm.showImageEnable.value = state.isFinished && !TextUtils.isEmpty(saveImageUri)
            }
        })

        vm.showImage.observe(this, Observer {
            if (!TextUtils.isEmpty(saveImageUri)) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(saveImageUri))
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.cancelWork()
    }
}
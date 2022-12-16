package com.idisfkj.androidapianalysis.motionlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.utils.ActivityUtils

class MotionLayoutActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(extraData.layoutId)
        title = extraData.title
    }

    private fun getExtraData(): MainModel =
        intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA) ?: throw NullPointerException("intent or extras is null")

}
package com.idisfkj.androidapianalysis.constraintlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.utils.ActivityUtils

/**
 * Created by idisfkj on 2018/4/24.
 * Email : idisfkj@gmail.com.
 */
class ConstraintLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(extraData.layoutId)
        title = extraData.title
    }

    private fun getExtraData(): MainModel =
            intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA) ?: throw NullPointerException("intent or extras is null")
}
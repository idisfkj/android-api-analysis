package com.idisfkj.androidapianalysis.binder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.utils.ActivityUtils

/**
 * Created by idisfkj on 2/4/21.
 * Email: idisfkj@gmail.com.
 */
class BinderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(extraData.layoutId)
        title = extraData.title
    }

    private fun getExtraData(): MainModel = intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA)
            ?: throw NullPointerException("intent or extras is null")

}
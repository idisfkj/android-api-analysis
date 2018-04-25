package com.idisfkj.androidapianalysis.constraintlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.idisfkj.androidapianalysis.utils.ActivityUtils

/**
 * Created by idisfkj on 2018/4/25.
 * Email : idisfkj@gmail.com.
 */
class ConstraintLyaoutItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(intent.extras.getInt(ActivityUtils.EXTRA_DATA))
        title = "ConstraintLayout"
    }
}
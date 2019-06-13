package com.idisfkj.androidapianalysis.constraintlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.idisfkj.androidapianalysis.utils.ActivityUtils

/**
 * Created by idisfkj on 2018/4/25.
 * Email : idisfkj@gmail.com.
 */
class ConstraintLayoutItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(intent.extras.getInt(ActivityUtils.EXTRA_DATA))
        title = "ConstraintLayout"
    }
}
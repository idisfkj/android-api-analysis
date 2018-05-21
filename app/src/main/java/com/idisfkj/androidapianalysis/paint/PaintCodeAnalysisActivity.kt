package com.idisfkj.androidapianalysis.paint

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.idisfkj.androidapianalysis.utils.ActivityUtils

/**
 * Created by idisfkj on 2018/5/21.
 * Email : idisfkj@gmail.com.
 */
class PaintCodeAnalysisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(intent.extras.getInt(ActivityUtils.EXTRA_DATA))
        title = "PaintCodeAnalysis"
    }

}
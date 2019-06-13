package com.idisfkj.androidapianalysis.paint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.idisfkj.androidapianalysis.utils.ActivityUtils

/**
 * Created by idisfkj on 2018/5/21.
 * Email : idisfkj@gmail.com.
 */
class PaintCodeAnalysisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.extras?.let {
            setContentView(it.getInt(ActivityUtils.EXTRA_DATA))
        }
        title = "PaintCodeAnalysis"
    }

}
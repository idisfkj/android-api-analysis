package com.idisfkj.androidapianalysis.paint

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.databinding.ActivityPaintLayoutBinding
import com.idisfkj.androidapianalysis.utils.ActivityUtils

/**
 * Created by idisfkj on 2018/5/19.
 * Email : idisfkj@gmail.com.
 */
class PaintActivity : AppCompatActivity(), View.OnClickListener {

    private val mBinding by lazy { ActivityPaintLayoutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(mBinding.root)
        title = extraData.title
        setupListener()
    }

    private fun setupListener() {
        mBinding.codeAnalysis.setOnClickListener(this)
    }

    private fun getExtraData(): MainModel = intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA) ?:
            throw NullPointerException("intent or extras is null")

    override fun onClick(v: View?) {
        val intent = Intent(this, PaintCodeAnalysisActivity::class.java)
        intent.putExtra(ActivityUtils.EXTRA_DATA, getLayoutFromId(v?.id))
        startActivity(intent)
    }


    private fun getLayoutFromId(id: Int?): Int = when (id) {
        R.id.code_analysis -> R.layout.activity_paint_code_analysis_layout
        else -> throw IllegalArgumentException("not supported the id: $id")
    }
}
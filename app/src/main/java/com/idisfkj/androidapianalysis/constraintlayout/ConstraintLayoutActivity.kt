package com.idisfkj.androidapianalysis.constraintlayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.databinding.ActivityConstraintLayoutBinding
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import com.idisfkj.androidapianalysis.webview.WebViewArticleActivity

/**
 * Created by idisfkj on 2018/4/24.
 * Email : idisfkj@gmail.com.
 */
class ConstraintLayoutActivity : AppCompatActivity(), View.OnClickListener {

    private val mBinding by lazy { ActivityConstraintLayoutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(mBinding.root)
        title = extraData.title
        setupListener()
    }

    private fun getExtraData(): MainModel =
            intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA) ?: throw NullPointerException("intent or extras is null")

    private fun setupListener() {
        mBinding.normal.setOnClickListener(this)
        mBinding.margin.setOnClickListener(this)
        mBinding.circle.setOnClickListener(this)
        mBinding.chain.setOnClickListener(this)
        mBinding.guideLine.setOnClickListener(this)
        mBinding.barrierAndGroup.setOnClickListener(this)
        mBinding.other.setOnClickListener(this)
        mBinding.relativeArticle.setOnClickListener {
            WebViewArticleActivity.navigationPage(this, "ConstraintLayout使用汇总", "https://rousetime.com/2018/05/03/ConstraintLayout%E4%BD%BF%E7%94%A8%E6%B1%87%E6%80%BB/")
        }
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ConstraintLayoutItemActivity::class.java)
        intent.putExtra(ActivityUtils.EXTRA_DATA, getLayoutFromId(v?.id))
        startActivity(intent)
    }

    private fun getLayoutFromId(id: Int?): Int = when (id) {
        R.id.normal -> R.layout.activity_constraint_layout_normal
        R.id.margin -> R.layout.activity_constraint_layout_margin
        R.id.circle -> R.layout.activity_constraint_layout_circle
        R.id.chain -> R.layout.activity_constraint_layout_chain
        R.id.guideLine -> R.layout.activity_constraint_layout_guide_line
        R.id.barrierAndGroup -> R.layout.activity_constraint_layout_barrier_group
        R.id.other -> R.layout.activity_constraint_layout_other
        else -> throw IllegalArgumentException("not supported the id: $id")
    }

}
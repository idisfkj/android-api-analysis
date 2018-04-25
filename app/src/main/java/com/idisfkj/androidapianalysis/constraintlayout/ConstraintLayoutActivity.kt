package com.idisfkj.androidapianalysis.constraintlayout

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_constraint_layout.*

/**
 * Created by idisfkj on 2018/4/24.
 * Email : idisfkj@gmail.com.
 */
class ConstraintLayoutActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(extraData.layoutId)
        title = extraData.title
        setupListener()
    }

    private fun getExtraData(): MainModel =
            intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA) ?: throw NullPointerException("intent or extras is null")

    private fun setupListener() {
        normal.setOnClickListener(this)
        margin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ConstraintLyaoutItemActivity::class.java)
        intent.putExtra(ActivityUtils.EXTRA_DATA, getLayoutFromId(v?.id))
        startActivity(intent)
    }

    private fun getLayoutFromId(id: Int?): Int = when (id) {
        R.id.normal -> R.layout.activity_constraint_layout_normal
        else -> throw IllegalArgumentException("not supported the id: $id")
    }

}
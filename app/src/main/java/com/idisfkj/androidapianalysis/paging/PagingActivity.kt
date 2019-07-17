package com.idisfkj.androidapianalysis.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.paging.adapter.PagingAdapter
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_paging.*

/**
 * Created by idisfkj on 2019-07-15.
 * Email : idisfkj@gmail.com.
 */
class PagingActivity : AppCompatActivity() {

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(PagingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(extraData.layoutId)
        title = extraData.title

        val adapter = PagingAdapter()
        recycler_view.adapter = adapter

        viewModel.articleList.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun getExtraData(): MainModel = intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA)
            ?: throw NullPointerException("intent or extras is null")
}
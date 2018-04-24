package com.idisfkj.androidapianalysis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MainRecyclerAdapter()
        adapter.addAll(ActivityUtils.getMainModels())
        recyclerView.adapter = adapter
    }
}

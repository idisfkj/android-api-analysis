package com.idisfkj.androidapianalysis

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by idisfkj on 2019-06-13.
 * Email : idisfkj@gmail.com.
 */
object BindingAdapter {

    @BindingAdapter("layout_manager")
    @JvmStatic
    fun setLayoutManager(recyclerView: RecyclerView, manager: RecyclerView.LayoutManager) {
        recyclerView.layoutManager = manager
    }
}
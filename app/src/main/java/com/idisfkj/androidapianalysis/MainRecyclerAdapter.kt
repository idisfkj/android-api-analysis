package com.idisfkj.androidapianalysis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by idisfkj on 2018/4/18.
 * Email : idisfkj@gmail.com.
 */
class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerItemVH>() {
    private val mDataList by lazy { arrayListOf<MainModel>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerItemVH = MainRecyclerItemVH(LayoutInflater.from(parent?.context).inflate(R.layout.main_recycler_item_layout, parent, false))

    override fun getItemCount(): Int = mDataList.size

    override fun onBindViewHolder(holder: MainRecyclerItemVH, position: Int) {
        holder.bindData(mDataList[position], position)
    }

    fun addAll(list: List<MainModel>) {
        mDataList.addAll(list)
    }

    fun getItemData(position: Int) = if (position < mDataList.size) mDataList[position]
            else throw IndexOutOfBoundsException("the position $position out of size ${mDataList.size}")
}
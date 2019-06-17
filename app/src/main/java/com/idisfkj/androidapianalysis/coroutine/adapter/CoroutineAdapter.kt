package com.idisfkj.androidapianalysis.coroutine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.coroutine.model.ArticleModel
import com.idisfkj.androidapianalysis.coroutine.vh.CoroutineVH
import com.idisfkj.androidapianalysis.coroutine.vm.CoroutineVHVM

/**
 * Created by idisfkj on 2019-06-13.
 * Email : idisfkj@gmail.com.
 */
class CoroutineAdapter : RecyclerView.Adapter<CoroutineVH>() {

    val list by lazy { mutableListOf<ArticleModel>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoroutineVH = CoroutineVH(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_coroutine_layout, parent, false),
            CoroutineVHVM(parent.context)
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CoroutineVH, position: Int) = holder.bind(list[position])

    fun addData(item: ArticleModel) {
        list.add(item)
        notifyItemInserted(list.size - 1)
    }

    fun addAllData(items: List<ArticleModel>) {
        list.addAll(items)
        notifyItemRangeInserted(list.size - items.size, items.size)
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }
}

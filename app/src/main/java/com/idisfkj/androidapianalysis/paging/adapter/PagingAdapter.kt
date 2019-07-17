package com.idisfkj.androidapianalysis.paging.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.model.ArticleModel
import com.idisfkj.androidapianalysis.paging.vh.PagingVH

/**
 * Created by idisfkj on 2019-07-15.
 * Email : idisfkj@gmail.com.
 */
class PagingAdapter : PagedListAdapter<ArticleModel, PagingVH>(diffCallbacks) {

    companion object {
        private val diffCallbacks = object : DiffUtil.ItemCallback<ArticleModel>() {

            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean = oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingVH = PagingVH(R.layout.item_paging_article_layout, parent)

    override fun onBindViewHolder(holder: PagingVH, position: Int) = holder.bind(getItem(position))
}
package com.idisfkj.androidapianalysis.news.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.news.model.ArticleModel
import com.idisfkj.androidapianalysis.news.model.LoadStatus
import com.idisfkj.androidapianalysis.news.model.Success
import com.idisfkj.androidapianalysis.news.vh.LoadVH
import com.idisfkj.androidapianalysis.news.vh.NewsVH

/**
 * Created by idisfkj on 2019-07-19.
 * Email : idisfkj@gmail.com.
 */
class NewsAdapter(private val retry: () -> Unit) : PagedListAdapter<ArticleModel, RecyclerView.ViewHolder>(diffCallbacks) {

    private var loadStatus: LoadStatus? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE) {
            NewsVH(parent, R.layout.item_news_layout)
        } else {
            LoadVH(parent, R.layout.item_load_layout, retry)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE) {
            (holder as NewsVH).bind(getItem(position))
        } else {
            (holder as LoadVH).bind(loadStatus)
        }
    }

    override fun getItemCount(): Int = super.getItemCount() + if (showLoadStatus()) 1 else 0

    override fun getItemViewType(position: Int): Int = if (showLoadStatus() && position == itemCount - 1) LOAD_TYPE else ITEM_TYPE

    fun updateLoadStatus(loadStatus: LoadStatus) {
        val hadLoad = showLoadStatus()
        this.loadStatus = loadStatus
        val hasLoad = showLoadStatus()
        if (hadLoad != hasLoad) {
            if (!hadLoad && hasLoad) {
                notifyItemInserted(super.getItemCount())
            } else {
                notifyItemRemoved(super.getItemCount())
            }
        } else {
            notifyItemChanged(super.getItemCount())
        }
    }

    private fun showLoadStatus() = loadStatus != null && loadStatus !is Success

    companion object {
        private const val ITEM_TYPE = 0x0001
        private const val LOAD_TYPE = 0x0002

        private val diffCallbacks = object : DiffUtil.ItemCallback<ArticleModel>() {

            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean = oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean = oldItem == newItem

        }
    }

}


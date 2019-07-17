package com.idisfkj.androidapianalysis.paging.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.idisfkj.androidapianalysis.model.ArticleModel
import kotlinx.android.synthetic.main.item_paging_article_layout.view.*

/**
 * Created by idisfkj on 2019-07-15.
 * Email : idisfkj@gmail.com.
 */
class PagingVH(@LayoutRes resId: Int, parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(resId, parent, false)
) {
    fun bind(articleModel: ArticleModel?) {
        with(itemView) {
            title.text = articleModel?.title
        }
    }
}
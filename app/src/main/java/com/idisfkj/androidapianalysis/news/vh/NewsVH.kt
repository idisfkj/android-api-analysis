package com.idisfkj.androidapianalysis.news.vh

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idisfkj.androidapianalysis.news.model.ArticleModel
import com.idisfkj.androidapianalysis.webview.WebViewArticleActivity
import kotlinx.android.synthetic.main.item_news_layout.view.*
import kotlinx.android.synthetic.main.item_paging_article_layout.view.title

/**
 * Created by idisfkj on 2019-07-19.
 * Email : idisfkj@gmail.com.
 */
class NewsVH(parent: ViewGroup, @LayoutRes layoutId: Int) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

    fun bind(model: ArticleModel?) {
        model?.let {
            with(itemView) {
                article_image.visibility = if (!TextUtils.isEmpty(it.urlToImage)) View.VISIBLE else View.INVISIBLE
                it.urlToImage?.let {
                    Glide.with(this).load(it).into(article_image)
                }
                title.text = it.title
                setOnClickListener { _ ->
                    WebViewArticleActivity.navigationPage(context, it.title, it.url)
                }
            }
        }
    }
}
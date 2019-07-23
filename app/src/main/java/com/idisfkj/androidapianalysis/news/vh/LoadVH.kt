package com.idisfkj.androidapianalysis.news.vh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.idisfkj.androidapianalysis.news.model.Error
import com.idisfkj.androidapianalysis.news.model.LoadStatus
import com.idisfkj.androidapianalysis.news.model.Loading
import com.idisfkj.androidapianalysis.news.model.NoMore
import kotlinx.android.synthetic.main.item_load_layout.view.*

/**
 * Created by idisfkj on 2019-07-23.
 * Email : idisfkj@gmail.com.
 */
class LoadVH(parent: ViewGroup, @LayoutRes layoutId: Int, private val retry: () -> Unit) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

    init {
        itemView.retry_button.setOnClickListener {
            retry.invoke()
        }
    }

    fun bind(model: LoadStatus?) {
        with(itemView) {
            progress_bar.visibility = if (model is Loading) View.VISIBLE else View.GONE
            error_msg.visibility = if (model is Error || model is NoMore) View.VISIBLE else View.GONE
            error_msg.text = when (model) {
                is Error -> model.message
                is NoMore -> model.content
                else -> ""
            }
            retry_button.visibility = if (model is Error) View.VISIBLE else View.GONE
        }
    }
}
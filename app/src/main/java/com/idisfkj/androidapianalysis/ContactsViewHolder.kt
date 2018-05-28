package com.idisfkj.androidapianalysis

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.contacts_item_layout.view.*

/**
 * Created by idisfkj on 2018/5/27.
 * Email : idisfkj@gmail.com.
 */
class ContactsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: ContactsModel?) {
        itemView.name.text = model?.name
    }
}
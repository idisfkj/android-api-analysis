package com.idisfkj.androidapianalysis

import android.support.v7.widget.RecyclerView
import android.view.View
import com.idisfkj.androidapianalysis.viewmodel.ContactsViewModel
import kotlinx.android.synthetic.main.contacts_item_layout.view.*

/**
 * Created by idisfkj on 2018/5/27.
 * Email : idisfkj@gmail.com.
 */
class ContactsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: ContactsModel?, viewModel: ContactsViewModel) {
        itemView.name.text = model?.name
        itemView.setOnClickListener { viewModel.itemEvent.value = model?.id ?: 1 }
    }
}
package com.idisfkj.androidapianalysis

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by idisfkj on 2018/5/25.
 * Email : idisfkj@gmail.com.
 */
class ContactsAdapter(private val context: Context) : RecyclerView.Adapter<ContactsViewHolder>() {

    private val mList: MutableList<ContactsModel> by lazy { mutableListOf<ContactsModel>() }

    override fun onBindViewHolder(holder: ContactsViewHolder?, position: Int) {
        holder?.bind(mList[position])
    }

    override fun getItemCount(): Int = mList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContactsViewHolder =
            ContactsViewHolder(LayoutInflater.from(context).inflate(R.layout.contacts_item_layout, parent, false))

    fun addAll(items: List<ContactsModel>) {
        mList.addAll(items)
        notifyDataSetChanged()
    }

}
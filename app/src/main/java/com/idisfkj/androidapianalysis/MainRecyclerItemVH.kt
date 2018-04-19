package com.idisfkj.androidapianalysis

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.idisfkj.androidapianalysis.bitmap.BitmapActivity
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import kotlinx.android.synthetic.main.main_recycler_item_layout.view.*

/**
 * Created by idisfkj on 2018/4/18.
 * Email : idisfkj@gmail.com.
 */
class MainRecyclerItemVH(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bindData(value: MainModel, position: Int) {
        itemView.content.apply {
            text = value.content
            setOnClickListener {
                navigationPage(context, value)
            }
        }
    }

    private fun navigationPage(context: Context, value: MainModel) {
        val intent = Intent(context, getClass(value.type))
        intent.putExtra(ActivityUtils.EXTRA_DATA, value)
        context.startActivity(intent)
    }

    private fun getClass(type: Int): Class<*> = when (type) {
        ActivityUtils.BITMAP_TYPE -> BitmapActivity::class.java
        else -> throw IllegalArgumentException("$type -> This type is not supported")
    }
}
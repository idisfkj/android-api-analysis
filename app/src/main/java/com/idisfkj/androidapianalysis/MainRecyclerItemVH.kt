package com.idisfkj.androidapianalysis

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import com.idisfkj.androidapianalysis.utils.LogUtils
import kotlinx.android.synthetic.main.main_recycler_item_layout.view.*

/**
 * Created by idisfkj on 2018/4/18.
 * Email : idisfkj@gmail.com.
 */
class MainRecyclerItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(value: MainModel, position: Int) {
        itemView.content.apply {
            text = value.content
            setOnClickListener {
                if (position == 0) {
                    requestPermission(context, value)
                } else {
                    navigationPage(context, value)
                }
            }
        }
    }

    private fun requestPermission(context: Context, value: MainModel) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                LogUtils.d("shouldShowPermissionRationale")
//                ActivityCompat.requestPermissions(context, Array(1){ Manifest.permission.WRITE_EXTERNAL_STORAGE}, MainActivity.WRITE_PERMISSION_CODE)
//            } else {
                LogUtils.d("request permission")
                ActivityCompat.requestPermissions(context as Activity, Array(1){ Manifest.permission.WRITE_EXTERNAL_STORAGE}, MainActivity.WRITE_PERMISSION_CODE)
//            }
        } else {
            LogUtils.d("permission was success of before")
            navigationPage(context, value)
        }
    }

    private fun navigationPage(context: Context, value: MainModel) {
        val intent = Intent(context, ActivityUtils.getClassFromType(value.type))
        intent.putExtra(ActivityUtils.EXTRA_DATA, value)
        context.startActivity(intent)
    }
}
package com.idisfkj.androidapianalysis.utils

import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R

/**
 * Created by idisfkj on 2018/4/19.
 * Email : idisfkj@gmail.com.
 */
class ActivityUtils {
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val BITMAP_TYPE = 1
        fun createMainModel(type: Int): MainModel = when (type) {
            BITMAP_TYPE -> MainModel("About of Bitmap", type, "Bitmap", R.layout.activity_bitmap)
            else -> throw IllegalArgumentException("$type -> This type is not supported")
        }
    }
}

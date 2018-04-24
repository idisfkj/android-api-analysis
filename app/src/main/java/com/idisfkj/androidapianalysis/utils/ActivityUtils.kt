package com.idisfkj.androidapianalysis.utils

import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.bitmap.BitmapActivity
import com.idisfkj.androidapianalysis.constraintlayout.ConstraintLayoutActivity

/**
 * Created by idisfkj on 2018/4/19.
 * Email : idisfkj@gmail.com.
 */
class ActivityUtils {
    companion object {
        val EXTRA_DATA = "extra_data"
        private val BITMAP_TYPE = 1
        private val CONSTRAINT_LAYOUT_TYPE = 2

        private fun createMainModel(type: Int): MainModel = when (type) {
            BITMAP_TYPE -> MainModel("About of Bitmap", type, "Bitmap", R.layout.activity_bitmap)
            CONSTRAINT_LAYOUT_TYPE -> MainModel("About of ConstraintLayout", type, "ConstraintLayout", R.layout.activity_constraint_layout)
            else -> throw IllegalArgumentException("$type -> This type is not supported")
        }

        fun getMainModels(): List<MainModel> = mutableListOf(
                ActivityUtils.createMainModel(ActivityUtils.BITMAP_TYPE),
                ActivityUtils.createMainModel(ActivityUtils.CONSTRAINT_LAYOUT_TYPE)
        )

        fun getClassFromType(type: Int): Class<*> = when (type) {
            ActivityUtils.BITMAP_TYPE -> BitmapActivity::class.java
            ActivityUtils.CONSTRAINT_LAYOUT_TYPE -> ConstraintLayoutActivity::class.java
            else -> throw IllegalArgumentException("$type -> This type is not supported")
        }
    }
}

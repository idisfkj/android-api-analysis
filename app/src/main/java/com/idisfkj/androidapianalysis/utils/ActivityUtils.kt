package com.idisfkj.androidapianalysis.utils

import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.bitmap.BitmapActivity
import com.idisfkj.androidapianalysis.constraintlayout.ConstraintLayoutActivity
import com.idisfkj.androidapianalysis.paint.PaintActivity

/**
 * Created by idisfkj on 2018/4/19.
 * Email : idisfkj@gmail.com.
 */
class ActivityUtils {
    companion object {
        const val EXTRA_DATA = "extra_data"
        private const val BITMAP_TYPE = 1
        private const val CONSTRAINT_LAYOUT_TYPE = 2
        private const val PAINT_TYPE = 3

        private fun createMainModel(type: Int): MainModel = when (type) {
            BITMAP_TYPE -> MainModel("About of Bitmap", type, "Bitmap", R.layout.activity_bitmap)
            CONSTRAINT_LAYOUT_TYPE -> MainModel("About of ConstraintLayout", type, "ConstraintLayout", R.layout.activity_constraint_layout)
            PAINT_TYPE -> MainModel("About of Paint", type, "Paint", R.layout.activity_paint_layout)
            else -> throw IllegalArgumentException("$type -> This type is not supported")
        }

        fun getMainModels(): List<MainModel> = mutableListOf(
                createMainModel(BITMAP_TYPE),
                createMainModel(CONSTRAINT_LAYOUT_TYPE),
                createMainModel(PAINT_TYPE)
        )

        fun getClassFromType(type: Int): Class<*> = when (type) {
            BITMAP_TYPE -> BitmapActivity::class.java
            CONSTRAINT_LAYOUT_TYPE -> ConstraintLayoutActivity::class.java
            PAINT_TYPE -> PaintActivity::class.java
            else -> throw IllegalArgumentException("$type -> This type is not supported")
        }
    }
}

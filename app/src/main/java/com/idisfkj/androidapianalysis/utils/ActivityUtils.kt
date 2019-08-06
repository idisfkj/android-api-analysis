package com.idisfkj.androidapianalysis.utils

import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.bitmap.BitmapActivity
import com.idisfkj.androidapianalysis.constraintlayout.ConstraintLayoutActivity
import com.idisfkj.androidapianalysis.paint.PaintActivity
import com.idisfkj.androidapianalysis.workmanager.BlurImageActivity
import com.idisfkj.androidapianalysis.workmanager.OtherWorkerActivity

/**
 * Created by idisfkj on 2018/4/19.
 * Email : idisfkj@gmail.com.
 */
class ActivityUtils {
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val BITMAP_TYPE = 1
        private const val CONSTRAINT_LAYOUT_TYPE = 2
        private const val PAINT_TYPE = 3
        const val BLUR_IMAGE_TYPE = 0x0004
        private const val OTHER_WORKER_TYPE = 0x0008

        private fun createMainModel(type: Int): MainModel = when (type) {
            BITMAP_TYPE -> MainModel("About of Bitmap", type, "Bitmap", R.layout.activity_bitmap)
            CONSTRAINT_LAYOUT_TYPE -> MainModel("About of ConstraintLayout", type, "ConstraintLayout", R.layout.activity_constraint_layout)
            PAINT_TYPE -> MainModel("About of Paint", type, "Paint", R.layout.activity_paint_layout)
            BLUR_IMAGE_TYPE -> MainModel("About of Blur Image Work", type, "Blur", R.layout.activity_blur_image)
            OTHER_WORKER_TYPE -> MainModel("About of Other Work", type, "Other Work", R.layout.activity_other_worker)
            else -> throw IllegalArgumentException("$type -> This type is not supported")
        }

        fun getMainModels(): List<MainModel> = mutableListOf(
                createMainModel(BITMAP_TYPE),
                createMainModel(CONSTRAINT_LAYOUT_TYPE),
                createMainModel(PAINT_TYPE),
                createMainModel(BLUR_IMAGE_TYPE),
                createMainModel(OTHER_WORKER_TYPE)
        )

        fun getClassFromType(type: Int): Class<*> = when (type) {
            BITMAP_TYPE -> BitmapActivity::class.java
            CONSTRAINT_LAYOUT_TYPE -> ConstraintLayoutActivity::class.java
            PAINT_TYPE -> PaintActivity::class.java
            BLUR_IMAGE_TYPE -> BlurImageActivity::class.java
            OTHER_WORKER_TYPE -> OtherWorkerActivity::class.java
            else -> throw IllegalArgumentException("$type -> This type is not supported")
        }
    }
}

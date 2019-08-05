package com.idisfkj.androidapianalysis.workmanager.work

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.work.WorkerParameters

/**
 * Created by idisfkj on 2019-08-02.
 * Email : idisfkj@gmail.com.
 */
class BlurImageWorker(appContext: Context, params: WorkerParameters) : BaseImageWorker(appContext, params) {

    override fun apply(bitmap: Bitmap): Bitmap {
        var renderScript: RenderScript? = null
        try {
            renderScript = RenderScript.create(applicationContext, RenderScript.ContextType.DEBUG)
            val ain = Allocation.createFromBitmap(renderScript, bitmap)
            val aout = Allocation.createTyped(renderScript, ain.type)

            ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript)).apply {
                setRadius(25f)
                setInput(ain)
                forEach(aout)
            }

            val output = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
            aout.copyTo(output)

            return output
        } finally {
            renderScript?.finish()
        }
    }
}
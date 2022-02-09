package com.rousetime.opengles

/**
 * Created by Rouse.
 */

enum class ScaleType(val type: String) {
    FIX_XY("fitXY"),
    CENTER_FIT("centerFull")
}

data class GLRect(
    val left: Int,
    val top: Int,
    val width: Int,
    val height: Int
)

object Utils {

    fun adapterCoordinate(
        array: FloatArray,
        fw: Int,
        fh: Int,
        sw: Int,
        sh: Int,
        fitType: ScaleType
    ): FloatArray {
        return if (fitType == ScaleType.CENTER_FIT) {
            if (fw <= sw && fh <= sh) {
                // 中心对齐，不拉伸
                val gw = (sw - fw) / 2
                val gh = (sh - fh) / 2
                create(sw, sh, GLRect(gw, gh, fw, fh), array)
            } else { // centerCrop
                val fScale = fw * 1.0f / fh
                val sScale = sw * 1.0f / sh
                val srcRect = if (fScale > sScale) {
                    val w = sw
                    val x = 0
                    val h = (sw / fScale).toInt()
                    val y = (sh - h) / 2
                    GLRect(x, y, w, h)
                } else {
                    val h = sh
                    val y = 0
                    val w = (sh * fScale).toInt()
                    val x = (sw - w) / 2
                    GLRect(x, y, w, h)
                }
                create(sw, sh, srcRect, array)
            }
        } else { // 默认 fitXY
            create(fw, fh, GLRect(0, 0, fw, fh), array)
        }
    }
    
    fun create(width: Int, height: Int, rect: GLRect, array: FloatArray): FloatArray {

        // x0
        array[0] = rect.left.toFloat() / width
        // y0
        array[1] = (rect.top.toFloat() + rect.height) / height

        // x1
        array[2] = (rect.left.toFloat() + rect.width) / width
        // y1
        array[3] = (rect.top.toFloat() + rect.height) / height

        // x2
        array[4] = rect.left.toFloat() / width
        // y2
        array[5] = rect.top.toFloat() / height

        // x3
        array[6] = (rect.left.toFloat() + rect.width) / width
        // y3
        array[7] = rect.top.toFloat() / height

        return array
    }

}
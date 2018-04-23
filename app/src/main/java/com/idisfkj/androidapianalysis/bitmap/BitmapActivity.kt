package com.idisfkj.androidapianalysis.bitmap

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import com.idisfkj.androidapianalysis.utils.LogUtils
import kotlinx.android.synthetic.main.activity_bitmap.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

/**
 * Created by idisfkj on 2018/4/19.
 * Email : idisfkj@gmail.com.
 */
class BitmapActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var scaleBitmap: Bitmap
    private val mRootPath = Environment.getExternalStorageDirectory().absolutePath + "/androidApiAnalysis/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(extraData.layoutId)
        title = extraData.title
        setupListener()
        initBitmap()
    }

    private fun getExtraData(): MainModel =
            intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA) ?: throw NullPointerException("intent or extras is null")

    private fun setupListener() {
        create_bitmap.setOnClickListener(this)
        create_scaled_bitmap.setOnClickListener(this)
        decode_file.setOnClickListener(this)
        relative_article.setOnClickListener(this)
    }

    private fun initBitmap() {
        val options = BitmapFactory.Options()
        options.inSampleSize = 2
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        scaleBitmap = BitmapFactory.decodeResource(resources, R.drawable.yaodaoji, options)
        LogUtils.d("scaleBitmap of width %d and height %d. options of width %d and height %d.", scaleBitmap.width, scaleBitmap.height
                , options.outWidth, options.outHeight)
        LogUtils.d("scaleBitmap of byteCount %d and rowBytes %d", scaleBitmap.byteCount, scaleBitmap.rowBytes)

        //compress
        val path = mRootPath + "bitmap"
        val file = File(mRootPath)
        try {
            if (!file.exists()) {
                file.mkdirs()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val fs = FileOutputStream(path)
        val out = ByteArrayOutputStream()
        scaleBitmap.compress(Bitmap.CompressFormat.JPEG, 50, out)
        LogUtils.d("compressBitmap jpeg of byteCount %d.", out.toByteArray().size)
        fs.write(out.toByteArray())
        fs.close()
    }

    @SuppressLint("SetTextI18n")
    private fun createBitmap() {
        //bitmap
        val bitmapBitmap = Bitmap.createBitmap(scaleBitmap, 150, 0, 100, 100)
        image_view?.setImageBitmap(scaleBitmap)
        image_view_text.text = "width: " + scaleBitmap.width + " height: " + scaleBitmap.height
        sub_image_view.setImageBitmap(bitmapBitmap)
        sub_image_view_text.text = "startX: 150 startY: 0\n" + "width: " + bitmapBitmap.width + " height: " + bitmapBitmap.height
    }

    @SuppressLint("SetTextI18n")
    private fun createScaledBitmap() {
        //createScaledBitmap
        val createScaledBitmap = Bitmap.createScaledBitmap(scaleBitmap, 500, 300, false)
        image_view?.setImageBitmap(scaleBitmap)
        image_view_text.text = "width: " + scaleBitmap.width + " height: " + scaleBitmap.height
        sub_image_view.setImageBitmap(createScaledBitmap)
        sub_image_view_text.text = "width: " + createScaledBitmap.width + " height: " + createScaledBitmap.height
    }

    @SuppressLint("SetTextI18n")
    private fun decodeFile() {
        val decodeFileOptions = BitmapFactory.Options()
        val decodeFileBitmap = BitmapFactory.decodeFile(mRootPath + "bitmap", decodeFileOptions)
        decodeFileOptions.inSampleSize = 2
        val decodeFileScaleBitmap = BitmapFactory.decodeFile(mRootPath + "bitmap", decodeFileOptions)
        image_view?.setImageBitmap(decodeFileBitmap)
        image_view_text.text = "width: " + decodeFileBitmap.width + " height: " + decodeFileBitmap.height
        sub_image_view.setImageBitmap(decodeFileScaleBitmap)
        sub_image_view_text.text = "width: " + decodeFileScaleBitmap.width + " height: " + decodeFileScaleBitmap.height
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.create_bitmap -> {
                createBitmap()
            }
            R.id.create_scaled_bitmap -> {
                createScaledBitmap()
            }
            R.id.decode_file -> {
                decodeFile()
            }
            R.id.relative_article -> {

            }
        }
    }

}
package com.idisfkj.androidapianalysis.bitmap

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.databinding.ActivityBitmapBinding
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import com.idisfkj.androidapianalysis.utils.LogUtils
import com.idisfkj.androidapianalysis.webview.WebViewArticleActivity
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

/**
 * Created by idisfkj on 2018/4/19.
 * Email : idisfkj@gmail.com.
 */
class BitmapActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var scaleBitmap: Bitmap
    private var bitmapBitmap: Bitmap? = null
    private var createScaledBitmap: Bitmap? = null
    private var decodeFileBitmap: Bitmap? = null
    private var decodeFileScaleBitmap: Bitmap? = null
    private val mRootPath by lazy { getExternalFilesDir(null)?.absolutePath + "/androidApiAnalysis/" }
    private val mBinding by lazy { ActivityBitmapBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(mBinding.root)
        title = extraData.title
        setupListener()
        initBitmap()
    }

    private fun getExtraData(): MainModel =
            intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA)
                    ?: throw NullPointerException("intent or extras is null")

    private fun setupListener() {
        mBinding.createBitmap.setOnClickListener(this)
        mBinding.createScaledBitmap.setOnClickListener(this)
        mBinding.decodeFile.setOnClickListener(this)
        mBinding.relativeArticle.setOnClickListener(this)
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
                val a = file.mkdirs()
                LogUtils.d("initBitmap: $a, $path")
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
        if (bitmapBitmap == null) {
            bitmapBitmap = Bitmap.createBitmap(scaleBitmap, 150, 0, 100, 100)
        }
        mBinding.imageView.setImageBitmap(scaleBitmap)
        mBinding.imageViewText.text = "width: " + scaleBitmap.width + " height: " + scaleBitmap.height
        mBinding.subImageView.setImageBitmap(bitmapBitmap)
        mBinding.subImageViewText.text = "startX: 150 startY: 0\n" + "width: " + bitmapBitmap?.width + " height: " + bitmapBitmap?.height
    }

    @SuppressLint("SetTextI18n")
    private fun createScaledBitmap() {
        //createScaledBitmap
        if (createScaledBitmap == null) {
            createScaledBitmap = Bitmap.createScaledBitmap(scaleBitmap, 500, 300, false)
        }
        mBinding.imageView.setImageBitmap(scaleBitmap)
        mBinding.imageViewText.text = "width: " + scaleBitmap.width + " height: " + scaleBitmap.height
        mBinding.subImageView.setImageBitmap(createScaledBitmap)
        mBinding.subImageViewText.text = "width: " + createScaledBitmap?.width + " height: " + createScaledBitmap?.height
    }

    @SuppressLint("SetTextI18n")
    private fun decodeFile() {
        if (decodeFileBitmap == null || decodeFileScaleBitmap == null) {
            val decodeFileOptions = BitmapFactory.Options()
            decodeFileBitmap = BitmapFactory.decodeFile(mRootPath + "bitmap", decodeFileOptions)
            decodeFileOptions.inSampleSize = 2
            decodeFileScaleBitmap = BitmapFactory.decodeFile(mRootPath + "bitmap", decodeFileOptions)
        }
        mBinding.imageView.setImageBitmap(decodeFileBitmap)
        mBinding.imageViewText.text = "width: " + decodeFileBitmap?.width + " height: " + decodeFileBitmap?.height
        mBinding.subImageView.setImageBitmap(decodeFileScaleBitmap)
        mBinding.subImageViewText.text = "width: " + decodeFileScaleBitmap?.width + " height: " + decodeFileScaleBitmap?.height
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
                WebViewArticleActivity.navigationPage(this, "Bitmap的图片压缩汇总", "https://rousetime.com/2018/03/21/Bitmap%E7%9A%84%E5%9B%BE%E7%89%87%E5%8E%8B%E7%BC%A9%E6%B1%87%E6%80%BB/")
            }
        }
    }

    override fun onDestroy() {
        scaleBitmap.recycle()
        bitmapBitmap?.recycle()
        createScaledBitmap?.recycle()
        decodeFileBitmap?.recycle()
        decodeFileScaleBitmap?.recycle()
        super.onDestroy()
    }

}
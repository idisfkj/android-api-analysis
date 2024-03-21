package com.idisfkj.androidapianalysis.webview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.idisfkj.androidapianalysis.databinding.ActivityWebViewArticleLayoutBinding

/**
 * Created by idisfkj on 2018/4/23.
 * Email : idisfkj@gmail.com.
 */
class WebViewArticleActivity : AppCompatActivity() {

    private var mTitle: String? = null
    private var mPath: String? = null
    private val mBinding by lazy { ActivityWebViewArticleLayoutBinding.inflate(layoutInflater) }

    companion object {
        private const val EXTRA_TITLE = "extra_title"
        private const val EXTRA_PATH = "extra_path"
        fun navigationPage(context: Context, title: String, path: String) {
            val intent = Intent(context, WebViewArticleActivity::class.java)
            intent.putExtra(EXTRA_TITLE, title)
            intent.putExtra(EXTRA_PATH, path)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        getExtraData()
        title = mTitle
        mBinding.webView.settings.domStorageEnabled = true
        mBinding.webView.settings.useWideViewPort = true            // 使用推荐窗口
        mBinding.webView.settings.loadWithOverviewMode = true       // 设置加载模式
        mBinding.webView.loadUrl(mPath ?: "")
    }

    private fun getExtraData() {
        intent?.apply {
            mTitle = getStringExtra(EXTRA_TITLE)
            mPath = getStringExtra(EXTRA_PATH)
        }
    }
}
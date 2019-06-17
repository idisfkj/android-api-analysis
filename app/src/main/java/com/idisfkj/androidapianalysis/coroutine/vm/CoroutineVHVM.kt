package com.idisfkj.androidapianalysis.coroutine.vm

import android.content.Context
import com.idisfkj.androidapianalysis.coroutine.model.ArticleModel
import com.idisfkj.androidapianalysis.webview.WebViewArticleActivity

/**
 * Created by idisfkj on 2019-06-13.
 * Email : idisfkj@gmail.com.
 */
class CoroutineVHVM(private val context: Context) {

    lateinit var model: ArticleModel

    fun itemClick() {
        WebViewArticleActivity.navigationPage(context, model.title, model.link)
    }
}
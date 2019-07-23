package com.idisfkj.androidapianalysis.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by idisfkj on 2019-07-18.
 * Email : idisfkj@gmail.com.
 */
class NewsApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Authorization", "6d3ea7fa6d8e4fdfa663b9e7f00fd408")
        return chain.proceed(builder.build())
    }
}
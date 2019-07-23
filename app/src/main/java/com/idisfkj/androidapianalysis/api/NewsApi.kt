package com.idisfkj.androidapianalysis.api

import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit

/**
 * Created by idisfkj on 2019-07-18.
 * Email : idisfkj@gmail.com.
 */
interface NewsApi {

    @GET("/v2/{endpoint}")
    fun newsGet(@Path(value = "endpoint", encoded = true) endpoint: String, @QueryMap params: Map<String, String>): Observable<NewsResponse>

    companion object {
        private const val NEWS_BASE_URL = "https://newsapi.org"
        private var sInstance: NewsApi? = null
        private var gson: Gson? = null
        fun getInstance(): NewsApi {
            if (sInstance == null) {
                val client = OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor())
                        .addNetworkInterceptor(NewsApiInterceptor())
                        .connectTimeout(5000, TimeUnit.MILLISECONDS)
                        .build()
                sInstance = Retrofit.Builder()
                        .baseUrl(NEWS_BASE_URL)
                        .client(client)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(NewsApi::class.java)
            }
            return sInstance!!
        }

        fun createGson(): Gson {
            if (gson == null) {
                gson = Gson()
            }
            return gson!!
        }
    }
}
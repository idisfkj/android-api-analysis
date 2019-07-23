package com.idisfkj.androidapianalysis.news.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.google.gson.Gson
import com.idisfkj.androidapianalysis.api.NewsApi
import com.idisfkj.androidapianalysis.news.model.*
import com.idisfkj.androidapianalysis.utils.LogUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

/**
 * Created by idisfkj on 2019-07-19.
 * Email : idisfkj@gmail.com.
 */
class NewsDataSource(private val newsApi: NewsApi,
                     private val domains: String,
                     private val retryExecutor: Executor) : PageKeyedDataSource<Int, ArticleModel>() {

    val loadStatus = MutableLiveData<LoadStatus>()
    val initStatus = MutableLiveData<LoadStatus>()
    
    private var retry: (() -> Any)? = null

    fun retryAll() {
        val internalRetry = retry
        retry = null
        internalRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }

    private fun <T> getEverything(domains: String, page: Int, clazz: Class<T>): Observable<T> {
        return newsApi.newsGet("everything", mutableMapOf(
                "domains" to domains,
                "page" to page.toString()
        )).subscribeOn(Schedulers.io()).flatMap {
            val jsonData = NewsApi.createGson().toJson(it)
            val data = Gson().fromJson<T>(jsonData, clazz)
            Observable.just(data)
        }.observeOn(AndroidSchedulers.mainThread()).doOnError {}
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ArticleModel>) {
        initStatus.postValue(Loading(""))
        CompositeDisposable().add(getEverything(domains, 1, ArticleListModel::class.java)
                .subscribeWith(object : DisposableObserver<ArticleListModel>() {
                    override fun onComplete() {
                        LogUtils.d("onComplete")
                    }

                    override fun onError(e: Throwable) {
                        LogUtils.d("onError")
                        retry = {
                            loadInitial(params, callback)
                        }
                        initStatus.postValue(Error(e.localizedMessage))
                    }

                    override fun onNext(t: ArticleListModel) {
                        LogUtils.d("onNext %d", t.articles.size)
                        initStatus.postValue(Success(200))
                        callback.onResult(t.articles, 1, 2)
                    }
                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleModel>) {
        loadStatus.postValue(Loading(""))
        CompositeDisposable().add(getEverything(domains, params.key, ArticleListModel::class.java)
                .subscribeWith(object : DisposableObserver<ArticleListModel>() {
                    override fun onComplete() {
                        LogUtils.d("onComplete")
                    }

                    override fun onError(e: Throwable) {
                        LogUtils.d("onError")
                        retry = {
                            loadAfter(params, callback)
                        }
                        loadStatus.postValue(Error(e.localizedMessage))
                    }

                    override fun onNext(t: ArticleListModel) {
                        LogUtils.d("onNext %d", t.articles.size)
                        loadStatus.postValue(Success(200))
                        callback.onResult(t.articles, params.key + 1)
                    }
                }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleModel>) {

    }
}
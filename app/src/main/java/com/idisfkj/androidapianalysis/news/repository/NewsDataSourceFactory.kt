package com.idisfkj.androidapianalysis.news.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.idisfkj.androidapianalysis.api.NewsApi
import com.idisfkj.androidapianalysis.news.model.ArticleModel
import java.util.concurrent.Executor

/**
 * Created by idisfkj on 2019-07-22.
 * Email : idisfkj@gmail.com.
 */
class NewsDataSourceFactory(private val newsApi: NewsApi,
                            private val domains: String,
                            private val executor: Executor) : DataSource.Factory<Int, ArticleModel>() {

    val dataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, ArticleModel> {
        val dataSource = NewsDataSource(newsApi, domains, executor)
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}
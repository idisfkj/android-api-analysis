package com.idisfkj.androidapianalysis.news.repository

import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.idisfkj.androidapianalysis.api.NewsApi
import com.idisfkj.androidapianalysis.news.model.NewsListingModel
import java.util.concurrent.Executor

/**
 * Created by idisfkj on 2019-07-18.
 * Email : idisfkj@gmail.com.
 */
class NewsRepository(private val newsApi: NewsApi,
                     private val domains: String,
                     private val executor: Executor) : BaseRepository<NewsListingModel> {

    override fun sendRequest(pageSize: Int): NewsListingModel {
        val newsDataSourceFactory = NewsDataSourceFactory(newsApi, domains, executor)
        val newsPagingList = newsDataSourceFactory.toLiveData(
                pageSize = pageSize,
                fetchExecutor = executor
        )
        val loadStatus = Transformations.switchMap(newsDataSourceFactory.dataSourceLiveData) {
            it.loadStatus
        }
        val initStatus = Transformations.switchMap(newsDataSourceFactory.dataSourceLiveData) {
            it.initStatus
        }
        return NewsListingModel(
                pagedList = newsPagingList,
                loadStatus = loadStatus,
                refreshStatus = initStatus,
                retry = {
                    newsDataSourceFactory.dataSourceLiveData.value?.retryAll()
                },
                refresh = {
                    newsDataSourceFactory.dataSourceLiveData.value?.invalidate()
                }
        )
    }

}
package com.idisfkj.androidapianalysis.news.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Created by idisfkj on 2019-07-18.
 * Email : idisfkj@gmail.com.
 */
sealed class BaseModel

data class ArticleModel(val author: String,
                        val title: String,
                        val url: String,
                        val urlToImage: String?) : BaseModel()

data class ArticleListModel(val articles: List<ArticleModel>)

data class NewsListingModel(val pagedList: LiveData<PagedList<ArticleModel>>,
                            val loadStatus: LiveData<LoadStatus>,
                            val refreshStatus: LiveData<LoadStatus>,
                            val retry: () -> Unit,
                            val refresh: () -> Unit)

sealed class LoadStatus : BaseModel()
data class Success(val status: Int) : LoadStatus()
data class NoMore(val content: String) : LoadStatus()
data class Loading(val content: String) : LoadStatus()
data class Error(val message: String) : LoadStatus()



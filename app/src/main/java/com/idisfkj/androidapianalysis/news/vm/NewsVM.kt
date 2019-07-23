package com.idisfkj.androidapianalysis.news.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.idisfkj.androidapianalysis.news.adapter.NewsAdapter
import com.idisfkj.androidapianalysis.news.model.NewsListingModel
import com.idisfkj.androidapianalysis.news.repository.BaseRepository

/**
 * Created by idisfkj on 2019-07-18.
 * Email : idisfkj@gmail.com.
 */
class NewsVM(app: Application, private val newsRepository: BaseRepository<NewsListingModel>) : AndroidViewModel(app) {

    private val newsListing = MutableLiveData<NewsListingModel>()

    val adapter = NewsAdapter {
        retry()
    }

    val newsLoadStatus = Transformations.switchMap(newsListing) {
        it.loadStatus
    }

    val refreshLoadStatus = Transformations.switchMap(newsListing) {
        it.refreshStatus
    }

    val articleList = Transformations.switchMap(newsListing) {
        it.pagedList
    }

    fun getData() {
        newsListing.value = newsRepository.sendRequest(20)
    }

    private fun retry() {
        newsListing.value?.retry?.invoke()
    }

    fun refresh() {
        newsListing.value?.refresh?.invoke()
    }
}
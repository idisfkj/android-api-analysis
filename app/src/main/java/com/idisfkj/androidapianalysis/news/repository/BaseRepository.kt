package com.idisfkj.androidapianalysis.news.repository

/**
 * Created by idisfkj on 2019-07-18.
 * Email : idisfkj@gmail.com.
 */
interface BaseRepository<T> {

    fun sendRequest(pageSize: Int): T
}
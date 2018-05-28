package com.idisfkj.androidapianalysis.livedatea

import android.arch.lifecycle.LiveData

/**
 * Created by idisfkj on 2018/5/27.
 * Email : idisfkj@gmail.com.
 */
class ExtendLiveData<T> : LiveData<T>() {

    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }
}
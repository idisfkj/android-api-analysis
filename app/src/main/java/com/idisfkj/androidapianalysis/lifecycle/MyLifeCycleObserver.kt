package com.idisfkj.androidapianalysis.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log

/**
 * Created by idisfkj on 2018/5/27.
 * Email : idisfkj@gmail.com.
 */
class MyLifeCycleObserver(private val lifecycle: Lifecycle) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() {
        Log.d("idisfkj", "currentState: " + lifecycle.currentState)
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
            Log.d("idisfkj", "------create------")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        Log.d("idisfkj", "currentState: " + lifecycle.currentState)
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            Log.d("idisfkj", "------start------")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        Log.d("idisfkj", "currentState: " + lifecycle.currentState)
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            Log.d("idisfkj", "------resume------")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        Log.d("idisfkj", "currentState: " + lifecycle.currentState)
        Log.d("idisfkj", "------pause------")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        Log.d("idisfkj", "currentState: " + lifecycle.currentState)
        Log.d("idisfkj", "------stop------")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        Log.d("idisfkj", "currentState: " + lifecycle.currentState)
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.DESTROYED)) {
            Log.d("idisfkj", "------destroy------")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun any() {
        Log.d("idisfkj", "currentState: " + lifecycle.currentState)
        Log.d("idisfkj", "------any------")
    }
}
package com.idisfkj.androidapianalysis.lifecycle

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import com.idisfkj.androidapianalysis.R

/**
 * Created by idisfkj on 2018/5/27.
 * Email : idisfkj@gmail.com.
 */
class LifecycleActivity : Activity(), LifecycleOwner {
    private lateinit var mMyLifecycle: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_layout)
        mMyLifecycle = LifecycleRegistry(this)
        mMyLifecycle.markState(Lifecycle.State.CREATED)
        lifecycle.addObserver(MyLifeCycleObserver(lifecycle))
    }

    override fun onStart() {
        super.onStart()
        mMyLifecycle.markState(Lifecycle.State.STARTED)
    }

    override fun getLifecycle(): Lifecycle = mMyLifecycle
}
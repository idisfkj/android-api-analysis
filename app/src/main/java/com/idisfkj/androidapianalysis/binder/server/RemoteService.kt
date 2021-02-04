package com.idisfkj.androidapianalysis.binder.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.idisfkj.androidapianalysis.binder.Goods
import com.idisfkj.androidapianalysis.utils.LogUtils

/**
 * Created by idisfkj on 2/4/21.
 * Email: idisfkj@gmail.com.
 */
class RemoteService : Service() {

    private val goodsList = arrayListOf<Goods>()

    private val goodsManager = object : Stub() {

        override fun addGoods(goods: Goods?) {
            goods?.let { goodsList.add(it) }
        }

        override fun getGoodsList(): List<Goods>? {
            return goodsList
        }
    }

    override fun onCreate() {
        super.onCreate()
        LogUtils.d("RemoteService: ${android.os.Process.myPid()}")
        goodsManager.addGoods(Goods(0, "shoe_0"))
    }

    override fun onBind(intent: Intent?): IBinder? {
        return goodsManager
    }

}
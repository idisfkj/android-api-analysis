package com.idisfkj.androidapianalysis.binder

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.binder.server.GoodsManager
import com.idisfkj.androidapianalysis.binder.server.RemoteService
import com.idisfkj.androidapianalysis.binder.server.Stub
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import com.idisfkj.androidapianalysis.utils.LogUtils
import kotlin.random.Random

/**
 * Created by idisfkj on 2/4/21.
 * Email: idisfkj@gmail.com.
 */
class BinderActivity : AppCompatActivity(), View.OnClickListener {

    private var goodsManager: GoodsManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(extraData.layoutId)
        title = extraData.title
        bindService()
        LogUtils.d("BinderActivity: ${android.os.Process.myPid()}")
    }

    private fun bindService() {
        val intent = Intent(this, RemoteService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            goodsManager = Stub.asInterface(service)
            if (goodsManager != null) {
                val goodsList = goodsManager?.getGoodsList()
                LogUtils.d("BinderActivity: ${goodsList?.size}")
            }
        }

    }

    private fun getExtraData(): MainModel = intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA)
            ?: throw NullPointerException("intent or extras is null")

    override fun onClick(v: View?) {
        val id = Random.nextInt(1000)
        goodsManager?.addGoods(Goods(id, "shoe_$id"))
        goodsManager?.getGoodsList()?.forEach {
            LogUtils.d("BinderActivity: id: ${it.id}, name: ${it.name}")
        }
    }

}
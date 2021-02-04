package com.idisfkj.androidapianalysis.binder.server

import android.os.Binder
import android.os.IBinder
import android.os.Parcel
import com.idisfkj.androidapianalysis.binder.Goods
import com.idisfkj.androidapianalysis.binder.proxy.GoodsProxy
import com.idisfkj.androidapianalysis.binder.server.GoodsManager.Companion.DESCRIPTION
import com.idisfkj.androidapianalysis.utils.LogUtils

/**
 * Created by idisfkj on 2/4/21.
 * Email: idisfkj@gmail.com.
 */
abstract class Stub : Binder(), GoodsManager {

    companion object {

        fun asInterface(binder: IBinder?): GoodsManager? {
            if (binder == null) return null
            val manager = binder.queryLocalInterface(DESCRIPTION) as? GoodsManager
            if (manager != null) return manager
            LogUtils.d("Stub: new GoodsProxy")
            return GoodsProxy(binder)
        }
    }

    init {
        this.attachInterface(this, DESCRIPTION)
    }

    override fun asBinder(): IBinder = this

    override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
        when (code) {
            IBinder.INTERFACE_TRANSACTION -> {
                data.writeString(DESCRIPTION)
                return true
            }

            GoodsManager.ADD_GOODS_TRANSACTION -> {
                data.enforceInterface(DESCRIPTION)
                var goods: Goods? = null
                if (data.readInt() != 0) {
                    goods = Goods.createFromParcel(data)
                }
                addGoods(goods)
                reply?.writeNoException()
                return true
            }

            GoodsManager.GET_GOODS_TRANSACTION -> {
                data.enforceInterface(DESCRIPTION)
                val list = getGoodsList()
                reply?.writeNoException()
                reply?.writeTypedList(list)
                return true
            }

        }
        return super.onTransact(code, data, reply, flags)
    }

}
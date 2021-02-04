package com.idisfkj.androidapianalysis.binder.proxy

import android.os.IBinder
import android.os.Parcel
import com.idisfkj.androidapianalysis.binder.Goods
import com.idisfkj.androidapianalysis.binder.server.GoodsManager

/**
 * Created by idisfkj on 2/4/21.
 * Email: idisfkj@gmail.com.
 */
class GoodsProxy(private val remote: IBinder) : GoodsManager {

    override fun addGoods(goods: Goods?) {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        try {
            data.writeInterfaceToken(GoodsManager.DESCRIPTION)
            if (goods != null) {
                data.writeInt(1)
                goods.writeToParcel(data, 0)
            } else {
                data.writeInt(0)
            }
            remote.transact(GoodsManager.ADD_GOODS_TRANSACTION, data, reply, 0)
            reply.readException()
        } finally {
            data.recycle()
            reply.recycle()
        }
    }

    override fun getGoodsList(): List<Goods>? {
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        val result: List<Goods>?

        try {
            data.writeInterfaceToken(GoodsManager.DESCRIPTION)
            remote.transact(GoodsManager.GET_GOODS_TRANSACTION, data, reply, 0)
            reply.readException()
            result = reply.createTypedArrayList(Goods.CREATOR)
        } finally {
            data.recycle()
            reply.recycle()
        }
        return result
    }

    override fun asBinder(): IBinder = remote
}
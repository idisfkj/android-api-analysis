package com.idisfkj.androidapianalysis.binder.server

import android.os.IBinder
import android.os.IInterface
import com.idisfkj.androidapianalysis.binder.Goods

/**
 * Created by idisfkj on 2/4/21.
 * Email: idisfkj@gmail.com.
 */
interface GoodsManager : IInterface {

    companion object {
        const val DESCRIPTION = "com.idisfkj.androidapianalysis.binder.server.GoodsManager"

        const val GET_GOODS_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION
        const val ADD_GOODS_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION + 1
    }

    fun addGoods(goods: Goods?)

    fun getGoodsList(): List<Goods>?

}
package com.idisfkj.androidapianalysis.proxy.statistic

import java.lang.reflect.Proxy

/**
 * Created by idisfkj on 2020/9/1.
 * Email: idisfkj@gmail.com.
 */
class Statistic private constructor() {

    companion object {
        @JvmStatic
        val instance by lazy { Statistic() }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(service.classLoader, arrayOf(service)) { proxy, method, args ->
            return@newProxyInstance LoadService(method).invoke(args)
        } as T
    }

}
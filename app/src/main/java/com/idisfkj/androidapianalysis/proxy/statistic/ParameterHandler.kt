package com.idisfkj.androidapianalysis.proxy.statistic

/**
 * Created by idisfkj on 2020/9/1.
 * Email: idisfkj@gmail.com.
 */
abstract class ParameterHandler<T> {

    abstract fun apply(parameterMap: MutableMap<String, T>, value: T)

    class Content<T>(private val index: Int, private val name: String) : ParameterHandler<T>() {

        override fun apply(parameterMap: MutableMap<String, T>, value: T) {
            parameterMap[name] = value
        }
    }

}
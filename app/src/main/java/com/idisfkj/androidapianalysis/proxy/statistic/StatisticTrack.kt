package com.idisfkj.androidapianalysis.proxy.statistic

import com.idisfkj.androidapianalysis.utils.LogUtils

/**
 * Created by idisfkj on 2020/9/2.
 * Email: idisfkj@gmail.com.
 */
class StatisticTrack private constructor() {

    companion object {
        @JvmStatic
        val instance by lazy { StatisticTrack() }
        const val SCAN_TYPE = "scan"
        const val CLICK_TYPE = "click"
    }

    class Parameter(var name: String = "", var time: Long? = null) {
        companion object {
            const val NAME = "name"
            const val TIME = "time"
        }
    }

    fun track(type: String, pageName: String, parameter: Parameter) {
        LogUtils.d(buildString {
            append("\n")
            append("methodType: $type")
            append("\n")
            append("pageName: $pageName")
            append("\n")
            append("parameter: key(${Parameter.NAME}) => value(${parameter.name})")
            if (parameter.time != null) {
                append("\n")
                append("parameter: key(${Parameter.TIME}) => value(${parameter.time})")
            }
        })
        // todo send statistic data to service
    }

    internal fun proxyTrack(type: String, pageName: String, parameterMap: Map<String, Any>) {
        val parameter = Parameter()
        parameterMap.forEach {
            if (Parameter.NAME == it.key) {
                parameter.name = it.value as String
            } else if (Parameter.TIME == it.key) {
                parameter.time = it.value as Long
            }
        }
        track(type, pageName, parameter)
    }

}
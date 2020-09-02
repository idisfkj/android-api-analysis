package com.idisfkj.androidapianalysis.proxy

import com.idisfkj.androidapianalysis.proxy.statistic.StatisticTrack
import com.idisfkj.androidapianalysis.proxy.statistic.annomation.Click
import com.idisfkj.androidapianalysis.proxy.statistic.annomation.Content
import com.idisfkj.androidapianalysis.proxy.statistic.annomation.Scan

/**
 * Created by idisfkj on 2020/9/1.
 * Email: idisfkj@gmail.com.
 */
interface StatisticService {

    @Scan(ProxyActivity.PAGE_NAME)
    fun buttonScan(@Content(StatisticTrack.Parameter.NAME) name: String)

    @Click(ProxyActivity.PAGE_NAME)
    fun buttonClick(@Content(StatisticTrack.Parameter.NAME) name: String, @Content(StatisticTrack.Parameter.TIME) clickTime: Long)

    @Scan(ProxyActivity.PAGE_NAME)
    fun textScan(@Content(StatisticTrack.Parameter.NAME) name: String)

    @Click(ProxyActivity.PAGE_NAME)
    fun textClick(@Content(StatisticTrack.Parameter.NAME) name: String, @Content(StatisticTrack.Parameter.TIME) clickTime: Long)
}

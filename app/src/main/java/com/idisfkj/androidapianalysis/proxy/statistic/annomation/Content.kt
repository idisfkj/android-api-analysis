package com.idisfkj.androidapianalysis.proxy.statistic.annomation

/**
 * Created by idisfkj on 2020/9/1.
 * Email: idisfkj@gmail.com.
 */

@MustBeDocumented
@Retention
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Content(val value: String)
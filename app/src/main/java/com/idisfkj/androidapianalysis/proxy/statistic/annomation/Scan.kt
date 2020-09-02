package com.idisfkj.androidapianalysis.proxy.statistic.annomation

/**
 * Created by idisfkj on 2020/9/1.
 * Email: idisfkj@gmail.com.
 */

@MustBeDocumented
@Retention
@Target(AnnotationTarget.FUNCTION)
annotation class Scan(val value: String)


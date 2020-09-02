package com.idisfkj.androidapianalysis.proxy.statistic

import com.idisfkj.androidapianalysis.proxy.statistic.StatisticTrack.Companion.CLICK_TYPE
import com.idisfkj.androidapianalysis.proxy.statistic.StatisticTrack.Companion.SCAN_TYPE
import com.idisfkj.androidapianalysis.proxy.statistic.annomation.Click
import com.idisfkj.androidapianalysis.proxy.statistic.annomation.Content
import com.idisfkj.androidapianalysis.proxy.statistic.annomation.Scan
import com.idisfkj.androidapianalysis.utils.LogUtils
import java.lang.reflect.Method
import java.lang.reflect.Type

/**
 * Created by idisfkj on 2020/9/1.
 * Email: idisfkj@gmail.com.
 */
class LoadService(private val method: Method) {

    private var mMethodAnnotations: Array<Annotation>? = null
    private var mParameterTypes: Array<Type>? = null
    private var mParameterAnnotations: Array<Array<Annotation>>? = null

    private var mMethodType: String = ""
    private var mPageName: String = ""
    private var mParameterHandlers: Array<ParameterHandler<*>>? = null
    private var mParameterMap = mutableMapOf<String, Any>()

    init {
        mMethodAnnotations = method.annotations
        mParameterTypes = method.genericParameterTypes
        mParameterAnnotations = method.parameterAnnotations
    }

    fun invoke(args: Array<out Any>) {
        LogUtils.d("\n methodAnnotations: ${mMethodAnnotations?.get(0)?.annotationClass} \n " +
                "parameterTypes: ${mParameterTypes?.get(0)?.toString()} \n " +
                "parameterAnnotations: ${mParameterAnnotations?.get(0)?.get(0)?.annotationClass}")

        parseAnnotations()
        call(args)

        // statistic
        StatisticTrack.instance.proxyTrack(mMethodType, mPageName, mParameterMap)

//        LogUtils.d(buildString {
//            append("\n")
//            append("methodType: $mMethodType")
//            append("\n")
//            append("pageName: $mPageName")
//            mParameterMap.forEach {
//                append("\n")
//                append("parameter: key(${it.key}) => value(${it.value})")
//            }
//        })
    }

    private fun call(args: Array<out Any>) {
        @Suppress("UNCHECKED_CAST")
        val handlers: Array<ParameterHandler<Any>> = mParameterHandlers as Array<ParameterHandler<Any>>
        if (args.size != mParameterHandlers?.size) {
            throw IllegalArgumentException("Argument count ( + ${args.size}) doesn't match expected count (${handlers.size})")
        }
        args.forEachIndexed { index, any ->
            handlers[index].apply(mParameterMap, any)
        }
    }

    private fun parseAnnotations() {
        mMethodAnnotations?.forEach {
            parseMethodAnnotations(it)
        }
        mParameterAnnotations?.let {
            mParameterHandlers = Array(it.size) {
                object : ParameterHandler<Any>() {
                    override fun apply(parameterMap: MutableMap<String, Any>, value: Any) {}
                }
            }
            it.forEachIndexed { index, arrayOfAnnotations ->
                mParameterHandlers?.set(index, parseParameter(index, mParameterTypes?.get(index), arrayOfAnnotations))
            }
        }
    }

    private fun parseMethodAnnotations(annotation: Annotation) {
        when (annotation) {
            is Scan -> {
                mMethodType = SCAN_TYPE
                mPageName = annotation.value
            }
            is Click -> {
                mMethodType = CLICK_TYPE
                mPageName = annotation.value
            }
            else -> {
            }
        }
    }

    private fun parseParameter(index: Int, type: Type?, annotations: Array<Annotation>): ParameterHandler<*> {
        var result: ParameterHandler<*>? = null
        for (annotation in annotations) {
            val parameterHandler = parseParameterAnnotations(index, type, annotation) ?: continue
            if (result != null) {
                throw IllegalArgumentException("$method parameter ${index + 1} Multiple Statistic annotations found, only one allowed.")
            }
            result = parameterHandler
        }
        if (result == null) {
            throw IllegalArgumentException("$method parameter ${index + 1} No Statistic annotations found.")
        }
        return result
    }

    private fun parseParameterAnnotations(index: Int, type: Type?, annotation: Annotation): ParameterHandler<*>? {
        if (annotation is Content) {
            // asset type to String
            return ParameterHandler.Content<String>(index, annotation.value)
        }
        // other annotation ...
        return null
    }
}
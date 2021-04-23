package com.rousetime.trace_plugin

/**
 * Created by idisfkj on 4/7/21.
 */
interface TransformProcess {

    fun process(entryName: String, sourceClassByte: ByteArray): ByteArray?
}
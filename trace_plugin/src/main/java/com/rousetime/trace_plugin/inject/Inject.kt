package com.rousetime.trace_plugin.inject

/**
 * Created by idisfkj on 4/8/21.
 */
interface Inject {

    fun modifyClassByte(byteArray: ByteArray): ByteArray

}
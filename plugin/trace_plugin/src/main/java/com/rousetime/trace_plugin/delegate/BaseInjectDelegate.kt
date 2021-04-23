package com.rousetime.trace_plugin.delegate

import com.rousetime.trace_plugin.inject.Inject

/**
 * Created by idisfkj on 4/8/21.
 */
abstract class BaseInjectDelegate {

    fun inject(byteArray: ByteArray): ByteArray {
        return createInject().modifyClassByte(byteArray)
    }

    abstract fun createInject(): Inject
}
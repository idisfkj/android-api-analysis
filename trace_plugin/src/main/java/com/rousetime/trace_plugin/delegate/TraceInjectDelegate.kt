package com.rousetime.trace_plugin.delegate

import com.rousetime.trace_plugin.inject.Inject
import com.rousetime.trace_plugin.inject.TraceAsmInject

/**
 * Created by idisfkj on 4/8/21.
 */
class TraceInjectDelegate: BaseInjectDelegate() {

    override fun createInject(): Inject = TraceAsmInject()

}
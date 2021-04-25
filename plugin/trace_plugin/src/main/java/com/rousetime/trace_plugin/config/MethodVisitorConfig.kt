package com.rousetime.trace_plugin.config

/**
 * Created by idisfkj on 4/25/21.
 */
open class MethodVisitorConfig {
    var visitAnnotation: (String?, Boolean) -> Unit? = {_, _ -> }
    var onMethodExit: (Int) -> Unit? = {_ -> }
    var name: String? = null
}

object LocalConfig {
    var methodVisitorConfig: MethodVisitorConfig? = null
}
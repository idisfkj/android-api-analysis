package com.rousetime.trace_plugin.filter

/**
 * Created by idisfkj on 4/9/21.
 */
interface ClassNameFilter {

    fun filter(className: String): Boolean

}
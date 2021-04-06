package com.rousetime.trace_plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by idisfkj on 4/7/21.
 */
class TracePlugin: Plugin<Project> {

    override fun apply(target: Project) {
        println("apply trace plugin")
    }

}
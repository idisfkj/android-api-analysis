package com.rousetime.trace_plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.rousetime.trace_plugin.config.LocalConfig
import com.rousetime.trace_plugin.config.MethodVisitorConfig
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by idisfkj on 4/7/21.
 */
class TracePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        println("Trace Plugin start to apply")
        if (target.plugins.hasPlugin(AppPlugin::class.java)) {
            val appExtension = target.extensions.getByType(AppExtension::class.java)
            appExtension.registerTransform(TraceTransform())
        }
        val methodVisitorConfig = target.extensions.create("methodVisitor", MethodVisitorConfig::class.java)
        LocalConfig.methodVisitorConfig = methodVisitorConfig
        target.afterEvaluate {
            println(methodVisitorConfig.name)
        }
    }

}
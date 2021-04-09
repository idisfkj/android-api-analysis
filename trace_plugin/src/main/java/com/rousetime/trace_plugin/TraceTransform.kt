package com.rousetime.trace_plugin

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.rousetime.trace_plugin.delegate.TraceInjectDelegate
import com.rousetime.trace_plugin.utils.ClassUtils

/**
 * Created by idisfkj on 4/7/21.
 */
class TraceTransform : Transform() {

    override fun getName(): String = this::class.java.simpleName

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> = TransformManager.CONTENT_JARS

    override fun isIncremental(): Boolean = false

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> = TransformManager.SCOPE_FULL_PROJECT

    override fun transform(transformInvocation: TransformInvocation?) {
        TransformProxy(transformInvocation, object : TransformProcess {
            override fun process(entryName: String, sourceClassByte: ByteArray): ByteArray? {
                // use ams to inject
                return if (ClassUtils.checkClassName(entryName)) {
                    TraceInjectDelegate().inject(sourceClassByte)
                } else {
                    null
                }
            }
        }).apply {
            transform()
        }
    }
}
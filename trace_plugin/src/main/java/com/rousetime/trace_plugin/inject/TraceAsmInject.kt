package com.rousetime.trace_plugin.inject

import com.rousetime.trace_plugin.visitor.ClassFilterVisitor
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter

/**
 * Created by idisfkj on 4/8/21.
 */
class TraceAsmInject : Inject {

    override fun modifyClassByte(byteArray: ByteArray): ByteArray {
        val classWriter = ClassWriter(ClassWriter.COMPUTE_MAXS)
        val classFilterVisitor = ClassFilterVisitor(classWriter)
        val classReader = ClassReader(byteArray)
        classReader.accept(classFilterVisitor, ClassReader.SKIP_DEBUG)
        return classWriter.toByteArray()
    }

}
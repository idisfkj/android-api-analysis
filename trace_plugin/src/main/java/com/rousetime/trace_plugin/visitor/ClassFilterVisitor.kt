package com.rousetime.trace_plugin.visitor

import com.rousetime.trace_plugin.utils.LogUtils
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

/**
 * Created by idisfkj on 4/8/21.
 */
class ClassFilterVisitor(cv: ClassVisitor?) : ClassVisitor(Opcodes.ASM5, cv) {

    private var mInterface: Array<out String>? = null

    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        LogUtils.d("visit => version: $version, access: $access, name: $name, signature: $signature, superName: $superName, interfaces: $interfaces")
        mInterface = interfaces
    }

    override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
        LogUtils.d("visitMethod => access: $access, name: $name, desc: $desc, signature: $signature, exceptions: $exceptions")
        if (mInterface != null && mInterface?.size ?: 0 > 0) {
            mInterface?.forEach {
                if ((name + desc) == "onClick(Landroid/view/View;)V" && it == "android/view/View\$OnClickListener") {
                    val mv = cv.visitMethod(access, name, desc, signature, exceptions)
                    return object : AdviceAdapter(Opcodes.ASM5, mv, access, name, desc) {

                        override fun onMethodEnter() {
                            super.onMethodEnter()
                            mv.visitFieldInsn(GETSTATIC, "com/idisfkj/androidapianalysis/utils/LogUtils", "INSTANCE", "Lcom/idisfkj/androidapianalysis/utils/LogUtils;")
                            mv.visitLdcInsn("inject success.")
                            mv.visitMethodInsn(INVOKEVIRTUAL, "com/idisfkj/androidapianalysis/utils/LogUtils", "d", "(Ljava/lang/String;)V", false)
                        }
                    }
                }
            }
        }
        return super.visitMethod(access, name, desc, signature, exceptions)
    }

    override fun visitField(access: Int, name: String?, desc: String?, signature: String?, value: Any?): FieldVisitor {
        LogUtils.d("visitField => access: $access, name: $name, desc: $desc, signature: $signature, vale: $value")
        return super.visitField(access, name, desc, signature, value)
    }
}
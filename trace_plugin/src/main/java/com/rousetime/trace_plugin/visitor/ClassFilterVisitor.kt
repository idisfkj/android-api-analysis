package com.rousetime.trace_plugin.visitor

import com.rousetime.trace_plugin.utils.LogUtils
import jdk.internal.org.objectweb.asm.Opcodes.ACC_FINAL
import jdk.internal.org.objectweb.asm.Opcodes.ACC_PRIVATE
import org.objectweb.asm.*
import org.objectweb.asm.commons.AdviceAdapter

/**
 * Created by idisfkj on 4/8/21.
 */
class ClassFilterVisitor(cv: ClassVisitor?) : ClassVisitor(Opcodes.ASM5, cv) {

    private var mClassName: String? = null
    private var mInterface: Array<out String>? = null

    private var mTrackDataName: String? = null
    private var mTrackDataValue: Any? = null
    private var mTrackDataDesc: String? = null

    private var mTrackScanDataName: String? = null
    private var mTrackScanDataDesc: String? = null
    private var mFieldPresent = false

    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
//        LogUtils.d("visit => version: $version, access: $access, name: $name, signature: $signature, superName: $superName, interfaces: $interfaces")
        mInterface = interfaces
        mClassName = name
    }

    override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
//        LogUtils.d("visitMethod => access: $access, name: $name, desc: $desc, signature: $signature, exceptions: $exceptions")
        val mv = cv.visitMethod(access, name, desc, signature, exceptions)
        return object : AdviceAdapter(Opcodes.ASM5, mv, access, name, desc) {

            private var mMethodAnnotationDesc: String? = null

            override fun visitAnnotation(desc: String?, visible: Boolean): AnnotationVisitor {
                mMethodAnnotationDesc = desc
                return super.visitAnnotation(desc, visible)
            }

            override fun onMethodExit(opcode: Int) {
                super.onMethodExit(opcode)
//                LogUtils.d("onMethodExit: $mTrackDataName, $mTrackDataValue, $mTrackDataDesc")
                if (name == "<init>" && desc == "()V" && mFieldPresent) {
                    mv.visitVarInsn(ALOAD, 0)
                    mv.visitFieldInsn(GETSTATIC, "com/idisfkj/androidapianalysis/proxy/statistic/Statistic", "Companion", "Lcom/idisfkj/androidapianalysis/proxy/statistic/Statistic\$Companion;")
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/idisfkj/androidapianalysis/proxy/statistic/Statistic\$Companion", "getInstance", "()Lcom/idisfkj/androidapianalysis/proxy/statistic/Statistic;", false)
                    mv.visitLdcInsn(Type.getType("Lcom/idisfkj/androidapianalysis/proxy/StatisticService;"))
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/idisfkj/androidapianalysis/proxy/statistic/Statistic", "create", "(Ljava/lang/Class;)Ljava/lang/Object;", false)
                    mv.visitTypeInsn(CHECKCAST, "com/idisfkj/androidapianalysis/proxy/StatisticService")
                    mv.visitFieldInsn(PUTFIELD, "com/idisfkj/androidapianalysis/proxy/ProxyActivity", "mStatisticService", "Lcom/idisfkj/androidapianalysis/proxy/StatisticService;")
                } else if (mMethodAnnotationDesc == "Lcom/idisfkj/androidapianalysis/proxy/statistic/annomation/TrackClick;" && !mTrackDataName.isNullOrEmpty()) {
                    mv.visitFieldInsn(GETSTATIC, "com/idisfkj/androidapianalysis/utils/LogUtils", "INSTANCE", "Lcom/idisfkj/androidapianalysis/utils/LogUtils;")
                    mv.visitLdcInsn("inject track click success.")
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/idisfkj/androidapianalysis/utils/LogUtils", "d", "(Ljava/lang/String;)V", false)

                    mv.visitVarInsn(ALOAD, 0)
                    mv.visitFieldInsn(GETFIELD, mClassName, "mStatisticService", "Lcom/idisfkj/androidapianalysis/proxy/StatisticService;")
                    mv.visitVarInsn(ALOAD, 0)
                    mv.visitFieldInsn(GETFIELD, mClassName, mTrackDataName, mTrackDataDesc)
                    mv.visitMethodInsn(INVOKEVIRTUAL, mTrackDataDesc?.substring(1, (mTrackDataDesc?.length
                            ?: 0) - 1), "getName", "()Ljava/lang/String;", false)
                    mv.visitVarInsn(ALOAD, 0)
                    mv.visitFieldInsn(GETFIELD, mClassName, mTrackDataName, mTrackDataDesc)
                    mv.visitMethodInsn(INVOKEVIRTUAL, mTrackDataDesc?.substring(1, (mTrackDataDesc?.length ?: 0) - 1), "getTime", "()J", false)
                    mv.visitMethodInsn(INVOKEINTERFACE, "com/idisfkj/androidapianalysis/proxy/StatisticService", "trackClick", "(Ljava/lang/String;J)V", true)
                } else if (mMethodAnnotationDesc == "Lcom/idisfkj/androidapianalysis/proxy/statistic/annomation/TrackScan;" && !mTrackScanDataName.isNullOrEmpty()) {
                    when (mTrackScanDataDesc) {
                        "Ljava/util/List;" -> {
                            mv.visitFieldInsn(GETSTATIC, "com/idisfkj/androidapianalysis/utils/LogUtils", "INSTANCE", "Lcom/idisfkj/androidapianalysis/utils/LogUtils;")
                            mv.visitLdcInsn("inject track scan success.")
                            mv.visitMethodInsn(INVOKEVIRTUAL, "com/idisfkj/androidapianalysis/utils/LogUtils", "d", "(Ljava/lang/String;)V", false)

                            mv.visitVarInsn(ALOAD, 0)
                            mv.visitFieldInsn(GETFIELD, "com/idisfkj/androidapianalysis/proxy/ProxyActivity", "mTrackScanData", "Ljava/util/List;")
                            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true)
                            mv.visitVarInsn(ASTORE, 2)
                            val label3 = Label()
                            mv.visitLabel(label3)
                            mv.visitFrame(Opcodes.F_APPEND, 2, arrayOf(Opcodes.TOP, "java/util/Iterator"), 0, null)
                            mv.visitVarInsn(ALOAD, 2)
                            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
                            val label4 = Label()
                            mv.visitJumpInsn(IFEQ, label4)
                            mv.visitVarInsn(ALOAD, 2)
                            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
                            mv.visitTypeInsn(CHECKCAST, "com/idisfkj/androidapianalysis/proxy/TrackModel")
                            mv.visitVarInsn(ASTORE, 1)

                            mv.visitVarInsn(ALOAD, 0)
                            mv.visitFieldInsn(GETFIELD, "com/idisfkj/androidapianalysis/proxy/ProxyActivity", "mStatisticService", "Lcom/idisfkj/androidapianalysis/proxy/StatisticService;")
                            mv.visitVarInsn(ALOAD, 1)
                            mv.visitMethodInsn(INVOKEVIRTUAL, "com/idisfkj/androidapianalysis/proxy/TrackModel", "getName", "()Ljava/lang/String;", false)
                            mv.visitMethodInsn(INVOKEINTERFACE, "com/idisfkj/androidapianalysis/proxy/StatisticService", "trackScan", "(Ljava/lang/String;)V", true)

                            mv.visitJumpInsn(GOTO, label3)
                            mv.visitLabel(label4)

                            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
                        }
                        "Lcom/idisfkj/androidapianalysis/proxy/TrackModel;" -> {
                            mv.visitFieldInsn(GETSTATIC, "com/idisfkj/androidapianalysis/utils/LogUtils", "INSTANCE", "Lcom/idisfkj/androidapianalysis/utils/LogUtils;")
                            mv.visitLdcInsn("inject track scan success.")
                            mv.visitMethodInsn(INVOKEVIRTUAL, "com/idisfkj/androidapianalysis/utils/LogUtils", "d", "(Ljava/lang/String;)V", false)

                            mv.visitVarInsn(ALOAD, 0)
                            mv.visitFieldInsn(GETFIELD, "com/idisfkj/androidapianalysis/proxy/ProxyActivity", "mStatisticService", "Lcom/idisfkj/androidapianalysis/proxy/StatisticService;")
                            mv.visitVarInsn(ALOAD, 0)
                            mv.visitFieldInsn(GETFIELD, "com/idisfkj/androidapianalysis/proxy/ProxyActivity", "mTrackScanData", "Lcom/idisfkj/androidapianalysis/proxy/TrackModel;")
                            mv.visitMethodInsn(INVOKEVIRTUAL, "com/idisfkj/androidapianalysis/proxy/TrackModel", "getName", "()Ljava/lang/String;", false)
                            mv.visitMethodInsn(INVOKEINTERFACE, "com/idisfkj/androidapianalysis/proxy/StatisticService", "trackScan", "(Ljava/lang/String;)V", true)
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }

    override fun visitField(access: Int, name: String?, desc: String?, signature: String?, value: Any?): FieldVisitor {
        val filterVisitor = super.visitField(access, name, desc, signature, value)
        return object : FieldVisitor(Opcodes.ASM5, filterVisitor) {
            override fun visitAnnotation(annotationDesc: String?, visible: Boolean): AnnotationVisitor {
                if (annotationDesc == "Lcom/idisfkj/androidapianalysis/proxy/statistic/annomation/TrackClickData;") {
//                    LogUtils.d("visitField => access: $access, name: $name, desc: $desc, signature: $signature, vale: $value")
                    mTrackDataName = name
                    mTrackDataValue = value
                    mTrackDataDesc = desc
                    createFiled()
                } else if (annotationDesc == "Lcom/idisfkj/androidapianalysis/proxy/statistic/annomation/TrackScanData;") {
                    mTrackScanDataName = name
                    mTrackScanDataDesc = desc
                    createFiled()
                }
                return super.visitAnnotation(annotationDesc, visible)
            }
        }
    }

    private fun createFiled() {
        if (!mFieldPresent) {
            mFieldPresent = true
            val fieldVisitor = cv.visitField(ACC_PRIVATE or ACC_FINAL, "mStatisticService", "Lcom/idisfkj/androidapianalysis/proxy/StatisticService;", null, null)
            fieldVisitor.visitEnd()
        }
    }
}
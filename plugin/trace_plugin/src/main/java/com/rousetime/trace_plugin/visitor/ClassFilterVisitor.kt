package com.rousetime.trace_plugin.visitor

import com.rousetime.trace_plugin.config.*
import org.objectweb.asm.*
import org.objectweb.asm.Opcodes.*

import org.objectweb.asm.commons.AdviceAdapter

/**
 * Created by idisfkj on 4/8/21.
 */
class ClassFilterVisitor(cv: ClassVisitor?) : ClassVisitor(Opcodes.ASM5, cv) {

    companion object {
        const val INIT_METHOD_NAME = "<init>"
        const val INIT_METHOD_DESC = "()V"
        const val LIST_DESC = "Ljava/util/List;"
    }

    private var mClassName: String? = null
    private var mInterface: Array<out String>? = null

    private var mTrackDataName: String? = null
    private var mTrackDataValue: Any? = null
    private var mTrackDataDesc: String? = null

    private var mTrackScanDataName: String? = null
    private var mTrackScanDataDesc: String? = null
    private var mFieldPresent = false
    private val statisticServiceField = StatisticService.INSTANCE.statisticService

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
                LocalConfig.methodVisitorConfig?.visitAnnotation?.invoke(desc, visible)
                mMethodAnnotationDesc = desc
                return super.visitAnnotation(desc, visible)
            }

            override fun onMethodExit(opcode: Int) {
                super.onMethodExit(opcode)
                LocalConfig.methodVisitorConfig?.onMethodExit?.invoke(opcode)
//                LogUtils.d("onMethodExit: $mTrackDataName, $mTrackDataValue, $mTrackDataDesc")

                // 默认构造方法init
                if (name == INIT_METHOD_NAME /** && desc == INIT_METHOD_DESC **/ && mFieldPresent) {
                    // 注入：向默认构造方法中，实例化statisticService
                    injectStatisticService(mv, Statistic.INSTANCE, statisticServiceField.copy(owner = mClassName ?: ""))
                } else if (mMethodAnnotationDesc == TRACK_CLICK_DESC && !mTrackDataName.isNullOrEmpty()) {
                    // 注入：日志
                    injectLogUtils(mv, defaultLogUtilsConfig.copy(ldc = "inject track click success."))

                    // 注入：trackClick 点击
                    injectTrackClick(mv, TrackModel.INSTANCE, StatisticService.INSTANCE)
                } else if (mMethodAnnotationDesc == TRACK_SCAN_DESC && !mTrackScanDataName.isNullOrEmpty()) {
                    when (mTrackScanDataDesc) {
                        // 数据类型为List<*>
                        LIST_DESC -> {
                            // 注入：日志
                            injectLogUtils(mv, defaultLogUtilsConfig.copy(ldc = "inject track scan success."))

                            // 注入：List 类型的TrackScan 曝光
                            injectListTrackScan(mv, TrackModel.INSTANCE, StatisticService.INSTANCE)
                        }
                        // 数据类型为TrackModel
                        TrackModel.DESC -> {
                            // 注入：日志
                            injectLogUtils(mv, defaultLogUtilsConfig.copy(ldc = "inject track scan success."))

                            // 注入: TrackScan 曝光
                            injectTrackScan(mv, TrackModel.INSTANCE, StatisticService.INSTANCE)
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
                if (annotationDesc == TRACK_CLICK_DATA_DESC) {  // TrackClickData 注解
//                    LogUtils.d("visitField => access: $access, name: $name, desc: $desc, signature: $signature, vale: $value")
                    mTrackDataName = name
                    mTrackDataValue = value
                    mTrackDataDesc = desc
                    createFiled()
                } else if (annotationDesc == TRACK_SCAN_DATA_DESC) { // TrackScanData注解
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
            // 注入：statisticService 字段
            val fieldVisitor = cv.visitField(ACC_PRIVATE or ACC_FINAL, statisticServiceField.name, statisticServiceField.desc, null, null)
            fieldVisitor.visitEnd()
        }
    }

    /**
     * 注入：日志
     */
    private fun injectLogUtils(mv: MethodVisitor, config: LogUtilsConfig) {
        mv.visitFieldInsn(GETSTATIC, config.owner, config.fileName, config.fileDesc)
        mv.visitLdcInsn(config.ldc)
        mv.visitMethodInsn(INVOKEVIRTUAL, config.owner, config.methodName, config.methodDesc, false)
    }

    /**
     * 注入：实例化statisticService
     */
    private fun injectStatisticService(mv: MethodVisitor, statistic: Statistic, statisticServiceField: FieldConfig) {
        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(statistic.companion.opcode, statistic.companion.owner, statistic.companion.name, statistic.companion.desc)
        mv.visitMethodInsn(statistic.getInstance.opcode, statistic.getInstance.owner, statistic.getInstance.name, statistic.getInstance.desc, false)
        mv.visitLdcInsn(Type.getType(StatisticService.DESC))
        mv.visitMethodInsn(statistic.create.opcode, statistic.create.owner, statistic.create.name, statistic.create.desc, false)
        mv.visitTypeInsn(CHECKCAST, StatisticService.OWNER)
        mv.visitFieldInsn(statisticServiceField.opcode, mClassName, statisticServiceField.name, statisticServiceField.desc)
    }

    /**
     * 注入：trackClick 点击
     */
    private fun injectTrackClick(mv: MethodVisitor, trackModel: TrackModel, statisticService: StatisticService) {
        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, mClassName, statisticServiceField.name, statisticServiceField.desc)
        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, mClassName, mTrackDataName, mTrackDataDesc)
        mv.visitMethodInsn(trackModel.getName.opcode, trackModel.getName.owner, trackModel.getName.name, trackModel.getName.desc, false)
        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, mClassName, mTrackDataName, mTrackDataDesc)
        mv.visitMethodInsn(trackModel.getTime.opcode, trackModel.getTime.owner, trackModel.getTime.name, trackModel.getTime.desc, false)
        mv.visitMethodInsn(statisticService.trackClick.opcode, statisticService.trackClick.owner, statisticService.trackClick.name, statisticService.trackClick.desc, true)
    }

    /**
     * 注入：List 类型的TrackScan 曝光
     */
    private fun injectListTrackScan(mv: MethodVisitor, trackModel: TrackModel, statisticService: StatisticService) {
        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, mClassName, mTrackScanDataName, mTrackScanDataDesc)
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
        mv.visitTypeInsn(CHECKCAST, TrackModel.OWNER)
        mv.visitVarInsn(ASTORE, 1)

        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, mClassName, statisticServiceField.name, statisticServiceField.desc)
        mv.visitVarInsn(ALOAD, 1)
        mv.visitMethodInsn(trackModel.getName.opcode, trackModel.getName.owner, trackModel.getName.name, trackModel.getName.desc, false)
        mv.visitMethodInsn(statisticService.trackScan.opcode, statisticService.trackScan.owner, statisticService.trackScan.name, statisticService.trackScan.desc, true)

        mv.visitJumpInsn(GOTO, label3)
        mv.visitLabel(label4)

        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
    }

    /**
     * 注入: TrackScan 曝光
     */
    private fun injectTrackScan(mv: MethodVisitor, trackModel: TrackModel, statisticService: StatisticService) {
        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, mClassName, statisticServiceField.name, statisticServiceField.desc)
        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, mClassName, mTrackScanDataName, mTrackScanDataDesc)
        mv.visitMethodInsn(trackModel.getName.opcode, trackModel.getName.owner, trackModel.getName.name, trackModel.getName.desc, false)
        mv.visitMethodInsn(statisticService.trackScan.opcode, statisticService.trackScan.owner, statisticService.trackScan.name, statisticService.trackScan.desc, true)
    }
}
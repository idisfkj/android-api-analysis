package com.rousetime.trace_plugin.config

import jdk.internal.org.objectweb.asm.Opcodes

/**
 * Created by idisfkj on 4/15/21.
 */
class StatisticService private constructor() {

    companion object {
        const val OWNER = "com/idisfkj/androidapianalysis/proxy/StatisticService"
        const val DESC = "Lcom/idisfkj/androidapianalysis/proxy/StatisticService;"

        val INSTANCE = StatisticService()
    }

    val statisticService = FieldConfig(
            Opcodes.PUTFIELD,
            "",
            "mStatisticService",
            DESC
    )

    val trackClick = MethodConfig(
            Opcodes.INVOKEINTERFACE,
            OWNER,
            "trackClick",
            "(Ljava/lang/String;J)V"
    )

    val trackScan = MethodConfig(
            Opcodes.INVOKEINTERFACE,
            OWNER,
            "trackScan",
            "(Ljava/lang/String;)V"
    )
}
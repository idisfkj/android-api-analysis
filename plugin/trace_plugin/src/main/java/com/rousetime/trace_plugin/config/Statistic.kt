package com.rousetime.trace_plugin.config

import org.objectweb.asm.*

/**
 * Created by idisfkj on 4/15/21.
 */
class Statistic private constructor(){

    companion object {
        const val OWNER = "com/idisfkj/androidapianalysis/proxy/statistic/Statistic"
        const val DESC = "Lcom/idisfkj/androidapianalysis/proxy/statistic/Statistic;"

        val INSTANCE  = Statistic()
    }

    val companion = FieldConfig(
            Opcodes.GETSTATIC,
            OWNER,
            COMPANION_NAME,
            buildCompanionDesc(DESC)
    )

    val getInstance = MethodConfig(
            Opcodes.INVOKEVIRTUAL,
            buildCompanionOwner(OWNER),
            "getInstance",
            "()Lcom/idisfkj/androidapianalysis/proxy/statistic/Statistic;"
    )

    val create = MethodConfig(
            Opcodes.INVOKEVIRTUAL,
            OWNER,
            "create",
            "(Ljava/lang/Class;)Ljava/lang/Object;"
    )

}
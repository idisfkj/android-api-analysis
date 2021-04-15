package com.rousetime.trace_plugin.config

import jdk.internal.org.objectweb.asm.Opcodes

/**
 * Created by idisfkj on 4/15/21.
 */
class TrackModel {
    companion object {
        const val OWNER = "com/idisfkj/androidapianalysis/proxy/TrackModel"
        const val DESC = "Lcom/idisfkj/androidapianalysis/proxy/TrackModel;"

        fun getInstance() = TrackModel()
    }

    val getName = MethodConfig(
            Opcodes.INVOKEVIRTUAL,
            OWNER,
            "getName",
            "()Ljava/lang/String;"
    )

    val getTime = MethodConfig(
            Opcodes.INVOKEVIRTUAL,
            OWNER,
            "getTime",
            "()J"
    )
}
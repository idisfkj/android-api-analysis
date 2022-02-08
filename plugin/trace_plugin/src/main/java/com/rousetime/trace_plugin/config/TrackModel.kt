package com.rousetime.trace_plugin.config

import org.objectweb.asm.*

/**
 * Created by idisfkj on 4/15/21.
 */
class TrackModel private constructor() {
    companion object {
        const val OWNER = "com/idisfkj/androidapianalysis/proxy/TrackModel"
        const val DESC = "Lcom/idisfkj/androidapianalysis/proxy/TrackModel;"

        val INSTANCE = TrackModel()
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
package com.rousetime.trace_plugin.config

/**
 * Created by idisfkj on 4/15/21.
 */

const val TRACK_CLICK_DESC = "Lcom/idisfkj/androidapianalysis/proxy/statistic/annomation/TrackClick;"
const val TRACK_SCAN_DESC = "Lcom/idisfkj/androidapianalysis/proxy/statistic/annomation/TrackScan;"
const val TRACK_CLICK_DATA_DESC = "Lcom/idisfkj/androidapianalysis/proxy/statistic/annomation/TrackClickData;"
const val TRACK_SCAN_DATA_DESC = "Lcom/idisfkj/androidapianalysis/proxy/statistic/annomation/TrackScanData;"

const val COMPANION_NAME = "Companion"

fun buildCompanionDesc(desc: String): String = desc.replace(";", "\$Companion;")

fun buildCompanionOwner(owner: String): String = "$owner\$Companion"

data class LogUtilsConfig(
        val owner: String,
        val fileName: String,
        val fileDesc: String,
        val methodName: String,
        val methodDesc: String,
        val ldc: String
)

val defaultLogUtilsConfig = LogUtilsConfig(
        "com/idisfkj/androidapianalysis/utils/LogUtils",
        "INSTANCE",
        "Lcom/idisfkj/androidapianalysis/utils/LogUtils;",
        "d",
        "(Ljava/lang/String;)V",
        ""
)

data class MethodConfig(
        val opcode: Int,
        val owner: String,
        val name: String,
        val desc: String
)

data class FieldConfig(
        val opcode: Int,
        var owner: String,
        val name: String,
        val desc: String
)


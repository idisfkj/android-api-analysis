package com.rousetime.trace_plugin.utils

import java.io.File
import java.io.FileOutputStream

/**
 * Created by idisfkj on 4/7/21.
 */
object ClassUtils {

    fun path2Classname(entryName: String): String? {
        return entryName.replace(File.separator, ".").replace(".class", "")
    }

    fun checkClassName(className: String): Boolean {
        return (!className.contains("R\\$") && !className.endsWith("R")
                && !className.endsWith("BuildConfig"))
    }

    fun saveFile(mTempDir: File?, modifiedClassBytes: ByteArray?): File? {
        var modified: File? = null
        try {
            if (modifiedClassBytes != null) {
                modified = mTempDir
                if (modified!!.exists()) {
                    modified.delete()
                }
                modified.createNewFile()
                FileOutputStream(modified).write(modifiedClassBytes)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return modified
    }
}
package com.rousetime.trace_plugin.utils

import com.rousetime.trace_plugin.TransformProcess
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.IOUtils
import java.io.File
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

/**
 * Created by idisfkj on 4/7/21.
 */
object JarFileUtils {

    fun modifyJarFile(jarFile: File, tempFile: File?, transformProcess: TransformProcess): File {
        // 设置输出jar
        val hexName = DigestUtils.md2Hex(jarFile.absolutePath).substring(0, 8)
        val modifyFile = File(tempFile, hexName + jarFile.name)
        val jarOutputStream = JarOutputStream(modifyFile.outputStream())

        // 读取之前的jar
        // 构建jarFile
        val originalFile = JarFile(jarFile)
        // 获取jarEntry
        val enumeration  = originalFile.entries()
        // 遍历jarEntry
        while (enumeration.hasMoreElements()) {
            val jarEntry = enumeration.nextElement()
            val inputStream = originalFile.getInputStream(jarEntry)

            val entryName = jarEntry.name
            // 构建zipEntry
            val zipEntry = ZipEntry(entryName)
            jarOutputStream.putNextEntry(zipEntry)

            var modifyClassByte: ByteArray? = null
            val sourceClassByte = IOUtils.toByteArray(inputStream)

            if (entryName.endsWith(".class")) {
//                LogUtils.d("modifyJarFile => $entryName")
                modifyClassByte = transformProcess.process(entryName, sourceClassByte)
            }

            if (modifyClassByte == null) {
                jarOutputStream.write(sourceClassByte)
            } else {
                jarOutputStream.write(modifyClassByte)
            }
            inputStream.close()
            jarOutputStream.closeEntry()
        }
        jarOutputStream.close()
        originalFile.close()

        return modifyFile
    }
}
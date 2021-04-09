package com.rousetime.trace_plugin

import com.android.build.api.transform.*
import com.android.utils.FileUtils
import com.rousetime.trace_plugin.filter.DefaultClassNameFilter
import com.rousetime.trace_plugin.utils.ClassUtils
import com.rousetime.trace_plugin.utils.JarFileUtils
import com.rousetime.trace_plugin.utils.LogUtils
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.IOUtils
import java.io.File
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.ForkJoinPool

/**
 * Created by idisfkj on 4/7/21.
 */
class TransformProxy(transformInvocation: TransformInvocation?, private val transformProcess: TransformProcess) : TransformProcess {

    private var inputs: Collection<TransformInput>? = null
    private var outputProvider: TransformOutputProvider? = null
    private var isIncremental: Boolean = false
    private var context: Context? = null
    private var executor: ExecutorService? = null
    private val tasks = ArrayList<Callable<Unit?>>()
    private val filter by lazy { DefaultClassNameFilter() }

    init {
        // 获取消费型输入，需要将结果传递给下一个transform
        inputs = transformInvocation?.inputs
        outputProvider = transformInvocation?.outputProvider
        isIncremental = transformInvocation?.isIncremental ?: false
        context = transformInvocation?.context
        executor = ForkJoinPool.commonPool()
    }

    fun transform() {
        if (!isIncremental) {
            outputProvider?.deleteAll()
        }
        inputs?.forEach {
            // jar
            it.jarInputs.forEach { jarInput ->
                transformJar(jarInput)
            }
            // directory
            it.directoryInputs.forEach { directoryInput ->
                transformDirectory(directoryInput)
            }
        }
        executor?.invokeAll(tasks)
    }

    private fun transformJar(jarInput: JarInput) {
        val status = jarInput.status
        var destName = jarInput.file.name
        if (destName.endsWith(".jar")) {
            destName = destName.substring(0, destName.length - 4)
        }
        // 重命名, 可能同名被覆盖
        val hexName = DigestUtils.md2Hex(jarInput.file.absolutePath).substring(0, 8)
        // 输出文件
        val dest = outputProvider?.getContentLocation(destName + "_" + hexName, jarInput.contentTypes, jarInput.scopes, Format.JAR)
        if (isIncremental) { // 增量
            // todo incremental
        } else {
            foreachJar(jarInput, dest)
        }
    }

    private fun foreachJar(jarInput: JarInput, dest: File?) {
        val file = JarFileUtils.modifyJarFile(jarInput.file, context?.temporaryDir, this)
        FileUtils.copyFile(file, dest)
    }

    private fun transformDirectory(directoryInput: DirectoryInput) {
        // 输出文件
        val dest = outputProvider?.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
        if (isIncremental) { // 增量
            // todo incremental
        } else {
            foreachFile(directoryInput.file, dest)
        }
    }

    private fun foreachFile(dir: File, dest: File?) {
        if (dir.isDirectory) {
            FileUtils.copyDirectory(dir, dest)
            FileUtils.getAllFiles(dir).forEach {
                if (it.name.endsWith(".class")) {
                    val task = Callable {
                        val absolutePath = it.absolutePath.replace(dir.absolutePath + File.separator, "")
                        val className = ClassUtils.path2Classname(absolutePath)
                        val bytes = IOUtils.toByteArray(it.inputStream())
                        val modifyClassByte = process(className ?: "", bytes)
                        // 保存修改的classFile
                        modifyClassByte?.let { byte -> saveClassFile(byte, dest, absolutePath) }
                    }
                    tasks.add(task)
                    executor?.submit(task)
                }
            }
        }
    }

    private fun saveClassFile(byteArray: ByteArray, dest: File?, absolutePath: String) {
        val tempDir = File(dest, "/temp")
        val tempFile = File(tempDir, absolutePath)
        tempFile.mkdirs()
        val modifyFile = ClassUtils.saveFile(tempFile, byteArray)
        val targetFile = File(dest, absolutePath)
        if (targetFile.exists()) {
            targetFile.delete()
        }
        FileUtils.copyDirectory(modifyFile, targetFile)
        tempFile.delete()
    }

    override fun process(entryName: String, sourceClassByte: ByteArray): ByteArray? {
        LogUtils.d("process => $entryName")
        if (!filter.filter(entryName)) {
            return transformProcess.process(entryName, sourceClassByte)
        }
        return null
    }
}
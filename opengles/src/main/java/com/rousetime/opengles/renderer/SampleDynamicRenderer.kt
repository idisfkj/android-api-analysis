package com.rousetime.opengles.renderer

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by Rouse.
 */
class SampleDynamicRenderer : GLSurfaceView.Renderer {

    private var mSurfaceViewWidth = 0
    private var mSurfaceViewHeight = 0

    companion object {
        private const val VERTEX_SHADER_SOURCE =
            "attribute vec4 a_Position;\n" +
                    "attribute vec4 a_Color;\n" +
                    "varying vec4 v_Color;\n" +
                    "void main() {\n" +
                    "   v_Color = a_Color;\n" +
                    "   gl_Position = a_Position;\n" +
                    "}"

        private const val FRAGMENT_SHADER_SOURCE =
            "precision mediump float;\n" +
                    "varying vec4 v_Color;\n" +
                    "void main() {\n" +
                    "   gl_FragColor = v_Color;\n" +
                    "}"

        private val mVertexData = floatArrayOf(
            0.0f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
            -0.5f, 1.0f, -1.0f, 0.5f, 0f, 0.5f
        )

        private const val VERTEX_DIMENSION_SIZE = 2

        private val mColorData = floatArrayOf(
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f
        )

        private const val COLOR_DIMENSION_SIZE = 4
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // 创建GL程序
        val programId = GLES20.glCreateProgram()
        // 创建顶点与片段着色器
        val vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER)
        val fragmentShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER)
        // 加载顶点与片段着色器代码
        GLES20.glShaderSource(vertexShader, VERTEX_SHADER_SOURCE)
        GLES20.glShaderSource(fragmentShader, FRAGMENT_SHADER_SOURCE)
        // 编译顶点与片段着色器代码
        GLES20.glCompileShader(vertexShader)
        GLES20.glCompileShader(fragmentShader)
        // 添加到GL程序中
        GLES20.glAttachShader(programId, vertexShader)
        GLES20.glAttachShader(programId, fragmentShader)
        // 链接GL程序
        GLES20.glLinkProgram(programId)
        // 应用GL程序
        GLES20.glUseProgram(programId)

        // 加载顶点数据
        val vertexBuffer = ByteBuffer.allocateDirect(mVertexData.size * Float.SIZE_BYTES)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        vertexBuffer.put(mVertexData)
        vertexBuffer.position(0)

        // 获取对应顶点参数位置
        val positionLocation = GLES20.glGetAttribLocation(programId, "a_Position")

        // 启动对应顶点参数位置
        GLES20.glEnableVertexAttribArray(positionLocation)

        // 填充顶点数据
        GLES20.glVertexAttribPointer(positionLocation, VERTEX_DIMENSION_SIZE, GLES20.GL_FLOAT, false, 0, vertexBuffer)

        // 加载颜色数据
        val colorBuffer = ByteBuffer.allocateDirect(mColorData.size * Float.SIZE_BYTES)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        colorBuffer.put(mColorData)
        colorBuffer.position(0)

        // 获取对应color参数位置
        val colorLocation = GLES20.glGetAttribLocation(programId, "a_Color")

        // 启动对应color参数位置
        GLES20.glEnableVertexAttribArray(colorLocation)

        // 填充对应顶点处color数据
        GLES20.glVertexAttribPointer(colorLocation, COLOR_DIMENSION_SIZE, GLES20.GL_FLOAT, false, 0, colorBuffer)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        mSurfaceViewWidth = width
        mSurfaceViewHeight = height
    }

    override fun onDrawFrame(gl: GL10?) {
        // 设置清屏颜色
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f)
        // 清屏处理
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        // 设置视图大小
        GLES20.glViewport(0, 0, mSurfaceViewWidth, mSurfaceViewHeight)
        // 渲染
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, mVertexData.size / VERTEX_DIMENSION_SIZE)
    }
}
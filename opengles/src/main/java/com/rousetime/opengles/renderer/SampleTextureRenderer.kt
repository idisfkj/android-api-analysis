package com.rousetime.opengles.renderer

import android.content.Context
import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import com.rousetime.opengles.R
import com.rousetime.opengles.ScaleType
import com.rousetime.opengles.Utils
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by Rouse.
 */
class SampleTextureRenderer(private val context: Context, private val glSurfaceView: GLSurfaceView) : GLSurfaceView.Renderer {

    private var mSurfaceViewWidth = 0
    private var mSurfaceViewHeight = 0

    companion object {
        private const val VERTEX_SHADER_SOURCE =
            "attribute vec4 a_Position;\n" +
                    "attribute vec2 a_textureCoordinate;\n" +
                    "varying vec2 v_textureCoordinate;\n" +
                    "void main() {\n" +
                    "   v_textureCoordinate = a_textureCoordinate;\n" +
                    "   gl_Position = a_Position;\n" +
                    "}"

        private const val FRAGMENT_SHADER_SOURCE =
            "precision mediump float;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "varying vec2 v_textureCoordinate;\n" +
                    "void main() {\n" +
                    "   gl_FragColor = texture2D(u_texture, v_textureCoordinate);\n" +
                    "}"

//        // 整个视图
//        private val mVertexData = floatArrayOf(
//            -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f,
//            -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f
//        )

        // 整个视图
        // --->
        //  \
        //   \
        // --->
        private val mVertexData = floatArrayOf(
            -1.0f, -1.0f,
            1.0f, -1.0f,
            -1.0f, 1.0f,
            1.0f, 1.0f
        )

        private const val VERTEX_DIMENSION_SIZE = 2

//        // 整个纹理
//        private val mTextureData = floatArrayOf(
//            0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f,
//            0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f
//        )

        // 整个纹理
        // --->
        //   /
        //  /
        // --->
        private val mTextureData = floatArrayOf(
            0.0f, 1.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f
        )

        private const val TEXTURE_DIMENSION_SIZE = 2
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

        // 将资源图片解码成bitmap
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.yaodaoji)

        // 加载纹理数据
        val textureBuffer = ByteBuffer.allocateDirect(mTextureData.size * Float.SIZE_BYTES)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        textureBuffer.put(
            // 根据图片进行纹理缩放
            Utils.adapterCoordinate(
                mTextureData,
                glSurfaceView.width,
                glSurfaceView.height,
                bitmap.width,
                bitmap.height,
                ScaleType.CENTER_FIT
            )
        )
        textureBuffer.position(0)

        // 获取对应纹理参数位置
        val textureCoordinateLocation = GLES20.glGetAttribLocation(programId, "a_textureCoordinate")

        // 启动对应纹理参数位置
        GLES20.glEnableVertexAttribArray(textureCoordinateLocation)

        // 填充对应顶点处纹理位置数据
        GLES20.glVertexAttribPointer(textureCoordinateLocation, TEXTURE_DIMENSION_SIZE, GLES20.GL_FLOAT, false, 0, textureBuffer)

        // 创建纹理
        val textures = IntArray(1)
        GLES20.glGenTextures(textures.size, textures, 0)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textures[0])

        // 设置纹理参数
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE)

        // 将bitmap填充到纹理中
        val bitmapBuffer = ByteBuffer.allocateDirect(bitmap.width * bitmap.height * 4)
            .order(ByteOrder.nativeOrder())
        bitmap.copyPixelsToBuffer(bitmapBuffer)
        bitmapBuffer.position(0)
        GLES20.glTexImage2D(
            GLES20.GL_TEXTURE_2D,
            0,
            GLES20.GL_RGBA,
            bitmap.width,
            bitmap.height,
            0,
            GLES20.GL_RGBA,
            GLES20.GL_UNSIGNED_BYTE,
            bitmapBuffer
        )

        // 绑定特定索引纹理
        val textureLocation = GLES20.glGetUniformLocation(programId, "u_texture")
        GLES20.glUniform1i(textureLocation, 0)
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
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, mVertexData.size / VERTEX_DIMENSION_SIZE)
    }
}
package com.rousetime.opengles

import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rousetime.opengles.renderer.SampleDynamicRenderer
import com.rousetime.opengles.renderer.SampleTextureRenderer
import com.rousetime.opengles.renderer.SampleTriangleRenderer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        findViewById<GLSurfaceView>(R.id.gl_surface_view).apply {
            setEGLConfigChooser(8, 8, 8, 8, 0, 0)
            setEGLContextClientVersion(2)
//            setRenderer(SampleTriangleRenderer())
//            setRenderer(SampleDynamicRenderer())
            setRenderer(SampleTextureRenderer(context, this))
        }
    }
}
package com.idisfkj.androidapianalysis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.idisfkj.butterknife.annotations.BindView
import com.idisfkj.butterknife.annotations.OnClick
import com.idisfkj.butterknife.bind.Butterknife

class MainActivity : AppCompatActivity() {

    @BindView(R.id.public_service, R.string.public_service)
    lateinit var sName: TextView

    @BindView(R.id.personal_wx, R.string.personal_wx)
    lateinit var sPhone: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Butterknife.bind(this)
    }

    @OnClick(R.id.public_service)
    fun nameClick(view: View) {
        Toast.makeText(this, getString(R.string.public_service_click_toast), Toast.LENGTH_LONG).show()
    }

    @OnClick(R.id.personal_wx)
    fun phoneClick(view: View) {
        Toast.makeText(this, getString(R.string.personal_wx_click_toast), Toast.LENGTH_LONG).show()
    }
}

package com.idisfkj.androidapianalysis

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.idisfkj.androidapianalysis.bitmap.BitmapActivity
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import com.idisfkj.androidapianalysis.utils.LogUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: MainRecyclerAdapter

    companion object {
        const val WRITE_PERMISSION_CODE = 4369
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = MainRecyclerAdapter()
        mAdapter.addAll(ActivityUtils.getMainModels())
        recyclerView.adapter = mAdapter
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            WRITE_PERMISSION_CODE -> {
                LogUtils.d("grantResults is $grantResults")
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LogUtils.d("permission granted")
                    val intent = Intent(this, BitmapActivity::class.java)
                    intent.putExtra(ActivityUtils.EXTRA_DATA, mAdapter.getItemData(0))
                    startActivity(intent)
                } else {
                    LogUtils.d("permission deny")
                    Toast.makeText(this, "I need WRITE_EXTERNAL_STORAGE permission", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

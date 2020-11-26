package com.idisfkj.androidapianalysis.datastore

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.lifecycleScope
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import com.idisfkj.androidapianalysis.utils.LogUtils
import kotlinx.android.synthetic.main.activity_data_store_layout.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by idisfkj on 2020/11/25.
 * Email: idisfkj@gmail.com.
 */
class DataStoreActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(extraData.layoutId)
        title = extraData.title
    }

    private fun getExtraData(): MainModel = intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA)
            ?: throw NullPointerException("intent or extras is null")

    private val dataStore = createDataStore("settings")

    companion object {
        val DATA_KEY = preferencesKey<String>("key_name")
        val DATA_KEY_INT = preferencesKey<Int>("key_name")
    }

    private suspend fun write(value: String) {
        dataStore.edit {
            it[DATA_KEY] = value
            LogUtils.d("dataStore write: $value")
        }
    }

    private suspend fun read() {
        dataStore.data.map {
            if (it[DATA_KEY] is String) {
                it[DATA_KEY] ?: ""
            } else {
                // unSafe
                "type is String: ${it[DATA_KEY] is String}"
            }
        }.collect {
            Toast.makeText(this@DataStoreActivity, "read result success: $it", Toast.LENGTH_LONG).show()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.write -> {
                lifecycleScope.launch { write(edit.text.toString()) }
            }
            R.id.read -> {
                lifecycleScope.launch { read() }
            }
            R.id.write_int -> {
                lifecycleScope.launch {
                    dataStore.edit {
                        it[DATA_KEY_INT] = -1
                    }
                }
            }
        }
    }
}
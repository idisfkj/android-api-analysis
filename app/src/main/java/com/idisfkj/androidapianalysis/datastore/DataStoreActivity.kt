package com.idisfkj.androidapianalysis.datastore

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.Serializer
import androidx.datastore.createDataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.lifecycleScope
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.R
import com.idisfkj.androidapianalysis.proto.Settings
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import com.idisfkj.androidapianalysis.utils.LogUtils
import kotlinx.android.synthetic.main.activity_data_store_layout.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by idisfkj on 2020/11/25.
 * Email: idisfkj@gmail.com.
 */
class DataStoreActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        setContentView(extraData.layoutId)
        title = extraData.title
        sp = getSharedPreferences("settings_preference", Context.MODE_PRIVATE)
        sp.edit().putString("key_name_from_sp", "from sp").apply()
    }

    private fun getExtraData(): MainModel = intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA)
            ?: throw NullPointerException("intent or extras is null")

    private val dataStore = createDataStore("settings", migrations = listOf(SharedPreferencesMigration(this, "settings_preference")))

    object SettingsSerializer : Serializer<Settings> {

        override fun readFrom(input: InputStream): Settings {
            return Settings.parseFrom(input)
        }

        override fun writeTo(t: Settings, output: OutputStream) {
            t.writeTo(output)
        }

    }

    private val dataStoreProto = createDataStore("settings.pb", SettingsSerializer)

    companion object {
        val DATA_KEY = preferencesKey<String>("key_name")
        val DATA_KEY_INT = preferencesKey<Int>("key_name")
        val DATA_KEY_FROM_SP = preferencesKey<String>("key_name_from_sp")
    }

    private suspend fun write(value: String) {
        dataStore.edit {
            it[DATA_KEY] = value
            LogUtils.d("dataStore write: $value")
        }
    }

    private suspend fun read() {
        dataStore.data.map {
            // unSafe type
            if (it[DATA_KEY] is String) {
                it[DATA_KEY] ?: ""
            } else {
                "type is String: ${it[DATA_KEY] is String}"
            }
        }.collect {
            Toast.makeText(this@DataStoreActivity, "read result success: $it", Toast.LENGTH_LONG).show()
        }
    }

    private suspend fun protoWrite(value: String) {
        dataStoreProto.updateData {
            it.toBuilder().setKeyName(value).build()
        }
    }

    private suspend fun protoRead() {
        dataStoreProto.data.map {
            // safe type
            it.keyName
        }.collect {
            Toast.makeText(this, "read result success form proto: $it", Toast.LENGTH_LONG).show()
        }
    }

    private suspend fun readFromOriginalSP() {
        dataStore.data.map {
            it[DATA_KEY_FROM_SP]
        }.collect {
            Toast.makeText(this, "read result success form original sp: $it", Toast.LENGTH_LONG).show()
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
            R.id.write_proto -> {
                lifecycleScope.launch {
                    protoWrite(edit_proto.text.toString())
                }
            }
            R.id.read_proto -> {
                lifecycleScope.launch { protoRead() }
            }
            R.id.read_from_sp -> {
                lifecycleScope.launch { readFromOriginalSP() }
            }
        }
    }
}
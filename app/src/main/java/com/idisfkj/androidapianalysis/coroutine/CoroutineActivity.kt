package com.idisfkj.androidapianalysis.coroutine

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.idisfkj.androidapianalysis.BR
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.coroutine.vm.CoroutineVM
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import kotlinx.coroutines.*

/**
 * Created by idisfkj on 2019-06-13.
 * Email : idisfkj@gmail.com.
 */
class CoroutineActivity : AppCompatActivity() {

    private lateinit var vm: CoroutineVM
    private lateinit var dataBinding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        dataBinding = DataBindingUtil.setContentView(this, extraData.layoutId)
        title = extraData.title
        init()
        getData()
    }

    private fun init() {
        vm = ViewModelProviders.of(this).get(CoroutineVM::class.java)
        dataBinding.setVariable(BR.vm, vm)
        dataBinding.executePendingBindings()
    }

    private fun getData() {
        vm.getAll()
        checkArticle()
    }

    private fun checkArticle() {
        vm.findByTitle("Android Architecture Components Part1:Room").observe(this, Observer {
        })
    }

    private fun getExtraData(): MainModel =
            intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA)
                    ?: throw NullPointerException("intent or extras is null")

    private fun showToast(content: String) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }

    private fun normalCallback() {
        Handler().postDelayed({
            showToast("normalCallback first")
            Handler().postDelayed({
                showToast("normalCallback second")
            }, 2000)
        }, 2000)
    }

    private fun coroutine() {
        lifecycleScope.launch {
            delay(2000)
            showToast("coroutine first")
            delay(2000)
            showToast("coroutine second")
        }
    }

    private suspend fun fetch() {
        val result = get("https://rousetime.com")
        showToast(result)
    }

    private suspend fun fetchAll() {
        coroutineScope {
            val deferredFirst = async { get("first") }
            val deferredSecond = async { get("second") }
            deferredFirst.await()
            deferredSecond.await()

//            val deferred = listOf(
//                    async { get("first") },
//                    async { get("second") }
//            )
//            deferred.awaitAll()
        }
    }

    private suspend fun get(url: String) = withContext(Dispatchers.IO) {
        // to do network request
        url
    }

}
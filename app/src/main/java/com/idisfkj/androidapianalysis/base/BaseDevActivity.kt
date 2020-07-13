package com.idisfkj.androidapianalysis.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.idisfkj.androidapianalysis.BR
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.utils.ActivityUtils

/**
 * Created by idisfkj on 2019-08-01.
 * Email : idisfkj@gmail.com.
 */
abstract class BaseDevActivity<V : ViewDataBinding, M : ViewModel> : AppCompatActivity() {

    protected lateinit var viewBinding: V
    protected lateinit var vm: M

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        viewBinding = DataBindingUtil.setContentView(this, getExtraData().layoutId)
        vm = ViewModelProviders.of(this).get(getViewModelClass())
//        viewBinding.setVariable(BR.vm, vm)
        viewBinding.lifecycleOwner = this
        title = extraData.title
    }

    private fun getExtraData(): MainModel = intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA)
            ?: throw NullPointerException("intent or extras is null")

    protected abstract fun getViewModelClass(): Class<M>
}
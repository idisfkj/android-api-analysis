package com.idisfkj.androidapianalysis.coroutine.vh

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.idisfkj.androidapianalysis.BR
import com.idisfkj.androidapianalysis.coroutine.model.ArticleModel
import com.idisfkj.androidapianalysis.coroutine.vm.CoroutineVHVM

/**
 * Created by idisfkj on 2019-06-13.
 * Email : idisfkj@gmail.com.
 */
class CoroutineVH(private val dataBinding: ViewDataBinding, private val vm: CoroutineVHVM) : RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(model: ArticleModel) {
        vm.model = model
        dataBinding.setVariable(BR.vm, vm)
    }
}
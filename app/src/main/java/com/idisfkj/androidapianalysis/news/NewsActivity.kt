package com.idisfkj.androidapianalysis.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.idisfkj.androidapianalysis.BR
import com.idisfkj.androidapianalysis.MainModel
import com.idisfkj.androidapianalysis.api.NewsApi
import com.idisfkj.androidapianalysis.databinding.ActivityNewsLayoutBinding
import com.idisfkj.androidapianalysis.news.model.Loading
import com.idisfkj.androidapianalysis.news.repository.NewsRepository
import com.idisfkj.androidapianalysis.news.vm.NewsVM
import com.idisfkj.androidapianalysis.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_news_layout.*
import java.util.concurrent.Executors

/**
 * Created by idisfkj on 2019-07-18.
 * Email : idisfkj@gmail.com.
 */
class NewsActivity : AppCompatActivity() {

    private lateinit var newsVM: NewsVM
    private lateinit var dataBinding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraData = getExtraData()
        dataBinding = DataBindingUtil.setContentView<ActivityNewsLayoutBinding>(this, extraData.layoutId)
        title = extraData.title
        setupViewModel()
        addObserve()
    }

    private fun setupViewModel() {
        newsVM = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return NewsVM(application, NewsRepository(
                        NewsApi.getInstance(),
                        "36kr.com",
                        Executors.newSingleThreadExecutor()
                )) as T
            }

        })[NewsVM::class.java]
        dataBinding.setVariable(BR.vm, newsVM)
        dataBinding.lifecycleOwner = this
    }

    private fun addObserve() {
        newsVM.articleList.observe(this, Observer {
            newsVM.adapter.submitList(it)
        })
        newsVM.newsLoadStatus.observe(this, Observer {
            newsVM.adapter.updateLoadStatus(it)
        })
        newsVM.refreshLoadStatus.observe(this, Observer {
            refresh_layout.isRefreshing = it is Loading
        })
        refresh_layout.setOnRefreshListener {
            newsVM.refresh()
        }
        newsVM.getData()
    }

    private fun getExtraData(): MainModel = intent?.extras?.getParcelable(ActivityUtils.EXTRA_DATA)
            ?: throw NullPointerException("intent or extras is null")

}
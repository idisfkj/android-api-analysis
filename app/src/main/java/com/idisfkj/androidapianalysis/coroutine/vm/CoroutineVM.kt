package com.idisfkj.androidapianalysis.coroutine.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.idisfkj.androidapianalysis.MyApp
import com.idisfkj.androidapianalysis.coroutine.adapter.CoroutineAdapter
import com.idisfkj.androidapianalysis.coroutine.dao.ArticleDao
import com.idisfkj.androidapianalysis.coroutine.model.ArticleModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * Created by idisfkj on 2019-06-13.
 * Email : idisfkj@gmail.com.
 */
class CoroutineVM(app: Application) : AndroidViewModel(app) {

    private var articleDao: ArticleDao = MyApp.db.articleDao()
    private val articleList = mutableListOf(
            ArticleModel(1, "Android Architecture Components Part1:Room", "https://www.rousetime.com/2018/06/07/Android-Architecture-Components-Part1-Room/"),
            ArticleModel(2, "Android Architecture Components Part2:LiveData", "https://www.rousetime.com/2018/06/10/Android-Architecture-Components-Part2-LiveData/"),
            ArticleModel(3, "Android Architecture Components Part3:Lifecycle", "https://www.rousetime.com/2018/06/14/Android-Architecture-Components-Part3-Lifecycle/"),
            ArticleModel(4, "Android Architecture Components Part4:ViewModel", "https://www.rousetime.com/2018/06/22/Android-Architecture-Components-Part4-ViewModel/")
    )

    val adapter = CoroutineAdapter()

    fun findByTitle(title: String) = liveData(Dispatchers.IO) {
        MyApp.db.articleDao().findByTitle(title)?.let {
            emit(it)
        }
    }

    fun getAll() {
        viewModelScope.launch {
            val articleList = withContext(Dispatchers.IO) {
                articleDao.getAll()
            }
            adapter.clear()
            adapter.addAllData(articleList)
        }
    }


    fun insertClick() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                articleList.forEach {
                    if (checkArticle(it.title)) {
                        MyApp.db.articleDao().insert(it)
                        return@withContext
                    }
                }
            }
            getAll()
        }
    }

    private fun checkArticle(title: String) = MyApp.db.articleDao().findByTitle(title) == null

    fun deleteClick() {
        if (adapter.list.size > 0) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    MyApp.db.articleDao().delete(adapter.list[Random.nextInt(0, adapter.list.size)])
                }
                getAll()
            }
        }
    }

    fun setLayoutManager() = LinearLayoutManager(getApplication())

}
package com.idisfkj.androidapianalysis.paging

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData
import com.idisfkj.androidapianalysis.AppDatabase
import com.idisfkj.androidapianalysis.dao.ArticleDao

/**
 * Created by idisfkj on 2019-07-15.
 * Email : idisfkj@gmail.com.
 */
class PagingViewModel(app: Application) : AndroidViewModel(app) {
    private val dao: ArticleDao by lazy { AppDatabase.getInstance(app).articleDao() }

    val articleList = dao.getAll()
            .toLiveData(Config(
                    pageSize = 5,
                    enablePlaceholders = true,
                    maxSize = 15
            ))
//        LivePagedListBuilder(dao.getAll(),
//                PagedList.Config.Builder()
//                        .setPageSize(5)
//                        .setEnablePlaceholders(true)
//                        .setMaxSize(20)
//                        .build())
//                .build()
}

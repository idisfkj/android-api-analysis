package com.idisfkj.androidapianalysis

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idisfkj.androidapianalysis.coroutine.dao.ArticleDao
import com.idisfkj.androidapianalysis.coroutine.model.ArticleModel

/**
 * Created by idisfkj on 2019-06-13.
 * Email : idisfkj@gmail.com.
 */
@Database(entities = [ArticleModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}
package com.idisfkj.androidapianalysis.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.idisfkj.androidapianalysis.model.ArticleModel

/**
 * Created by idisfkj on 2019-07-15.
 * Email : idisfkj@gmail.com.
 */
@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll(): DataSource.Factory<Int, ArticleModel>

    @Query("SELECT * FROM article WHERE title = :title LIMIT 1")
    fun findByTitle(title: String): ArticleModel?

    @Insert
    fun insertAll(articles: List<ArticleModel>)

    @Insert
    fun insert(article: ArticleModel)

    @Delete
    fun delete(article: ArticleModel)
}
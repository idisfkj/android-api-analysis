package com.idisfkj.androidapianalysis.coroutine.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.idisfkj.androidapianalysis.coroutine.model.ArticleModel

/**
 * Created by idisfkj on 2019-06-13.
 * Email : idisfkj@gmail.com.
 */
@Dao
interface ArticleDao {

    @Query("SELECT * FROM article_model")
    fun getAll(): List<ArticleModel>

    @Query("SELECT * FROM article_model WHERE title = :title LIMIT 1")
    fun findByTitle(title: String): ArticleModel?

    @Insert
    fun insertAll(vararg articles: ArticleModel)

    @Insert
    fun insert(article: ArticleModel)

    @Delete
    fun delete(article: ArticleModel)
}
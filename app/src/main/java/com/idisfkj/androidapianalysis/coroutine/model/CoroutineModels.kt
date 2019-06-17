package com.idisfkj.androidapianalysis.coroutine.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by idisfkj on 2019-06-13.
 * Email : idisfkj@gmail.com.
 */
@Entity(tableName = "article_model")
data class ArticleModel(@PrimaryKey val id: Int,
                        @ColumnInfo val title: String,
                        @ColumnInfo val link: String)
package com.idisfkj.androidapianalysis.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by idisfkj on 2019-07-15.
 * Email : idisfkj@gmail.com.
 */
@Entity(tableName = "article")
data class ArticleModel(@PrimaryKey(autoGenerate = true) val id: Int,
                        @ColumnInfo val title: String,
                        @ColumnInfo val link: String)
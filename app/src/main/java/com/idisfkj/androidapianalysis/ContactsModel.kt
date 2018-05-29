package com.idisfkj.androidapianalysis

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by idisfkj on 2018/5/25.
 * Email : idisfkj@gmail.com.
 */
@Entity(tableName = "contacts")
data class ContactsModel(
        @PrimaryKey
        @ColumnInfo(name = "contacts_id")
        val id: Int,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "phone")
        val phone: String
)

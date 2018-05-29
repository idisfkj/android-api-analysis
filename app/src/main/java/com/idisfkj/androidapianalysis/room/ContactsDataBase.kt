package com.idisfkj.androidapianalysis.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.idisfkj.androidapianalysis.ContactsModel

/**
 * Created by idisfkj on 2018/5/28.
 * Email : idisfkj@gmail.com.
 */
@Database(entities = arrayOf(ContactsModel::class), version = 1)
abstract class ContactsDataBase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

    companion object {
        private var instance: ContactsDataBase? = null
        fun getInstance(context: Context): ContactsDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        ContactsDataBase::class.java,
                        "contacts.db").build()
            }
            return instance as ContactsDataBase
        }
    }
}
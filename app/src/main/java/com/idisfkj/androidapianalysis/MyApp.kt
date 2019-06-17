package com.idisfkj.androidapianalysis

import android.app.Application
import androidx.room.Room

/**
 * Created by idisfkj on 2019-06-13.
 * Email : idisfkj@gmail.com.
 */
class MyApp : Application() {

    companion object {
        lateinit var db: AppDatabase
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "android-api-analysis-database.db"
        ).build()
    }
}
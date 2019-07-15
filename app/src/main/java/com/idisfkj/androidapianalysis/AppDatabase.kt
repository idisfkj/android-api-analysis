package com.idisfkj.androidapianalysis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.idisfkj.androidapianalysis.dao.ArticleDao
import com.idisfkj.androidapianalysis.model.ArticleModel
import java.util.concurrent.Executors

/**
 * Created by idisfkj on 2019-07-15.
 * Email : idisfkj@gmail.com.
 */
@Database(entities = [ArticleModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        private var sInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "android-api-analysis-database.db")
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                initData(context)
                            }
                        }).build()
            }
            return sInstance!!
        }

        private fun initData(context: Context) {
            Executors.newSingleThreadExecutor().execute {
                getInstance(context).articleDao().insertAll(
                        DEFAULT_ARTICLE.map {
                            ArticleModel(0, it.key, it.value)
                        }
                )
            }
        }
    }
}

private val DEFAULT_ARTICLE = mutableMapOf(
        Pair("EventBus的工作原理总结", "https://www.rousetime.com/2018/02/27/EventBus%E7%9A%84%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86%E6%80%BB%E7%BB%93/"),
        Pair("Bitmap的图片压缩汇总", "https://www.rousetime.com/2018/03/21/Bitmap%E7%9A%84%E5%9B%BE%E7%89%87%E5%8E%8B%E7%BC%A9%E6%B1%87%E6%80%BB/"),
        Pair("ConstraintLayout使用汇总", "https://www.rousetime.com/2018/05/03/ConstraintLayout%E4%BD%BF%E7%94%A8%E6%B1%87%E6%80%BB/"),
        Pair("Android Architecture Components Part1:Room", "https://www.rousetime.com/2018/06/07/Android-Architecture-Components-Part1-Room/"),
        Pair("Android Architecture Components Part2:LiveData", "https://www.rousetime.com/2018/06/10/Android-Architecture-Components-Part2-LiveData/"),
        Pair("Android Architecture Components Part3:Lifecycle", "https://www.rousetime.com/2018/06/14/Android-Architecture-Components-Part3-Lifecycle/"),
        Pair("Android Architecture Components Part4:ViewModel", "https://www.rousetime.com/2018/06/22/Android-Architecture-Components-Part4-ViewModel/"),
        Pair("自定义Android注解Part1:注解变量", "https://www.rousetime.com/2018/07/01/%E8%87%AA%E5%AE%9A%E4%B9%89Android%E6%B3%A8%E8%A7%A3Part1-%E6%B3%A8%E8%A7%A3%E5%8F%98%E9%87%8F/"),
        Pair("自定义Android注解Part2:代码自动生成", "https://www.rousetime.com/2018/07/04/%E8%87%AA%E5%AE%9A%E4%B9%89Android%E6%B3%A8%E8%A7%A3Part2-%E4%BB%A3%E7%A0%81%E8%87%AA%E5%8A%A8%E7%94%9F%E6%88%90/"),
        Pair("自定义Android注解Part3:绑定", "https://www.rousetime.com/2018/07/11/%E8%87%AA%E5%AE%9A%E4%B9%89Android%E6%B3%A8%E8%A7%A3Part3-%E7%BB%91%E5%AE%9A/"),
        Pair("ViewDragHelper之手势操作神器", "https://www.rousetime.com/2018/08/19/ViewDragHelper%E4%B9%8B%E6%89%8B%E5%8A%BF%E6%93%8D%E4%BD%9C%E7%A5%9E%E5%99%A8/"),
        Pair("5分钟吃透React Native Flexbox", "https://www.rousetime.com/2018/08/23/5%E5%88%86%E9%92%9F%E5%90%83%E9%80%8FReact-Native-Flexbox/"),
        Pair("React Native Fetch封装那点事...", "https://www.rousetime.com/2018/08/28/React-Native-Fetch%E5%B0%81%E8%A3%85%E9%82%A3%E7%82%B9%E4%BA%8B/"),
        Pair("你不该忽略的LaunchMode", "https://www.rousetime.com/2019/01/03/%E4%BD%A0%E4%B8%8D%E8%AF%A5%E5%BF%BD%E7%95%A5%E7%9A%84LaunchMode/"),
        Pair("vps携手hexo的博客搭建之旅", "https://www.rousetime.com/2019/01/06/vps%E6%90%BA%E6%89%8Bhexo%E7%9A%84%E5%8D%9A%E5%AE%A2%E6%90%AD%E5%BB%BA%E4%B9%8B%E6%97%85/"),
        Pair("Vue:scoped与module的使用与利弊", "https://www.rousetime.com/2019/01/11/Vue-scoped%E4%B8%8Emodule%E7%9A%84%E4%BD%BF%E7%94%A8%E4%B8%8E%E5%88%A9%E5%BC%8A/"),
        Pair("Android Gradle系列-入门篇", "https://www.rousetime.com/2019/05/05/Android-Gradle%E7%B3%BB%E5%88%97-%E5%85%A5%E9%97%A8%E7%AF%87/"),
        Pair("Android Gradle系列-原理篇", "https://www.rousetime.com/2019/05/13/Android-Gradle%E7%B3%BB%E5%88%97-%E5%8E%9F%E7%90%86%E7%AF%87/"),
        Pair("Android Gradle系列-运用篇", "https://www.rousetime.com/2019/05/23/Gradle%E7%B3%BB%E5%88%97-%E8%BF%90%E7%94%A8%E7%AF%87/"),
        Pair("Android Gradle系列-进阶篇", "https://www.rousetime.com/2019/06/03/Android-Gradle%E7%B3%BB%E5%88%97-%E8%BF%9B%E9%98%B6%E7%AF%87/"),
        Pair("Gson与List<T>对象间的相亲之旅", "https://www.rousetime.com/2019/05/08/Gson%E4%B8%8EList-T-%E5%AF%B9%E8%B1%A1%E9%97%B4%E7%9A%84%E7%9B%B8%E4%BA%B2%E4%B9%8B%E6%97%85/"),
        Pair("What? 你还不知道Kotlin Coroutine?", "https://www.rousetime.com/2019/06/17/What-%E4%BD%A0%E8%BF%98%E4%B8%8D%E7%9F%A5%E9%81%93Kotlin-Coroutine/")
)

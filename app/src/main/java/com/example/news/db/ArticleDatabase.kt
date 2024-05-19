package com.example.news.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news.models.Article


@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase:RoomDatabase() {

    abstract fun getArticleDao():ArticleDAO

    companion object{
        @Volatile
        private var instace:ArticleDatabase? = null // 資料庫的實例變數，使用 volatile 保證多執行緒間的可見性
        private var LOCK = Any() // 用於同步的鎖

        // 創建資料庫的靜態方法，使用雙重檢查鎖（Double-Checked Locking）確保唯一實例
        operator fun invoke(context: Context) = instace ?: synchronized(LOCK){
            instace ?: createDatabase(context).also{
                instace = it // 將新建立的資料庫實例賦值給 instace
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}
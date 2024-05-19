package com.example.news.repository

import androidx.room.Query
import com.example.news.api.RetrofitInstance
import com.example.news.db.ArticleDatabase
import com.example.news.models.Article
import java.util.Locale.IsoCountryCode

class NewsRepository (val db: ArticleDatabase){

    suspend fun getHeadlines(countryCode: String,pageNumber: Int) =
        RetrofitInstance.api.getHeadlines(countryCode,pageNumber)

    suspend fun searchNews(searchQuery: String,pageNumber: Int) =
        RetrofitInstance.api.serachForNews(searchQuery,pageNumber)

    suspend fun upset(article: Article) = db.getArticleDao().upsert(article)

    fun getFavouriteNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticles(article)
}
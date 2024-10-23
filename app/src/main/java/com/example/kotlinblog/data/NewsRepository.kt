package com.example.kotlinblog.data

import com.example.kotlinblog.BuildConfig
import com.example.kotlinblog.models.Article
import com.example.kotlinblog.models.NewsResponse
import com.example.kotlinblog.network.NewsApiService
import com.example.kotlinblog.ui.layout.homescreen.article

interface NewsRepository {
    suspend fun getHeadlines(): List<Article>

    suspend fun searchNews(query: String): List<Article>
}

class NetworkNewsRepository(private val apiService: NewsApiService) : NewsRepository {
    override suspend fun getHeadlines(): List<Article> {
        val response: NewsResponse = apiService.getHeadlines(apiKey = BuildConfig.API_KEY);
        return response.articles
    }

    override suspend fun searchNews(query: String): List<Article> {
        val articles = buildList<Article> { repeat(7) { add(article) } }
        return articles
    }
}
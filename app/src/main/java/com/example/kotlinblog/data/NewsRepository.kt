package com.example.kotlinblog.data

import com.example.kotlinblog.BuildConfig
import com.example.kotlinblog.models.Article
import com.example.kotlinblog.models.NewsResponse
import com.example.kotlinblog.network.NewsApiService
import com.example.kotlinblog.ui.layout.homescreen.article

interface NewsRepository {
    suspend fun getHeadlines(): List<Article>

    suspend fun searchNews(query: String): List<Article>

    suspend fun getTagNews(tag: String): List<Article>
}

class NetworkNewsRepository(private val apiService: NewsApiService) : NewsRepository {
    override suspend fun getHeadlines(): List<Article> {
        val response: NewsResponse = apiService.getHeadlines(apiKey = BuildConfig.API_KEY);
        return response.articles
    }

    override suspend fun searchNews(query: String): List<Article> {
        val response: NewsResponse =
            apiService.searchNews(query = query, apiKey = BuildConfig.API_KEY)
        return response.articles
    }

    override suspend fun getTagNews(tag: String): List<Article> {
        val response: NewsResponse = apiService.getTagNews(tag = tag, apiKey = BuildConfig.API_KEY)
        return response.articles
    }
}
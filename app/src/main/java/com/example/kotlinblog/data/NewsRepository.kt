package com.example.kotlinblog.data

import com.example.kotlinblog.models.Article
import com.example.kotlinblog.network.NewsApiService

interface NewsRepository {
    suspend fun getHeadlines(): List<Article> // https://newsapi.org/v2/top-headlines?country=us&apiKey=4ff940b0d27a4831b43c45d70c95edd7

    suspend fun searchNews(query: String): List<Article> // https://newsapi.org/v2/everything?q=query&sortBy=popularity&apiKey=API_KEY
}

class NetworkNewsRepository(apiService: NewsApiService) : NewsRepository {
    override suspend fun getHeadlines(): List<Article> {
        return emptyList()
    }

    override suspend fun searchNews(query: String): List<Article> {
        return emptyList()
    }
}
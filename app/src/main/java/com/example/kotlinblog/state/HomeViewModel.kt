package com.example.kotlinblog.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kotlinblog.KotlinBlogApplication
import com.example.kotlinblog.data.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _breakingNews = MutableStateFlow<BlogUiState>(BlogUiState.Loading)
    val breakingNews: StateFlow<BlogUiState> = _breakingNews.asStateFlow()

    private val _recommendedNews = MutableStateFlow<BlogUiState>(BlogUiState.Loading)
    val recommendedNews: StateFlow<BlogUiState> = _recommendedNews.asStateFlow()

    init {
        getHeadlines()
    }

    fun getHeadlines() {
        viewModelScope.launch {
            try {
                val headlines = newsRepository.getHeadlines()
                _breakingNews.value = BlogUiState.Success(headlines)
                _recommendedNews.value = BlogUiState.Success(headlines)
            } catch (e: Exception) {
                Log.e("HomeViewModel", e.toString())
                _breakingNews.value = BlogUiState.Error("Failed to load breaking news. Try again")
                _recommendedNews.value =
                    BlogUiState.Error("Failed to load recommended news. Try again")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as KotlinBlogApplication)
                val newsRepository = application.container.newsRepository
                HomeViewModel(newsRepository = newsRepository)
            }
        }
    }
}
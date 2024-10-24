package com.example.kotlinblog.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinblog.data.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class Tag(val value: String) {
    General("General"),
    Business("Business"),
    Entertainment("Entertainment"),
    Health("Health"),
    Science("Science"),
    Technology("Technology"),
    Sports("Sports"),
}

class SearchViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<BlogUiState>(BlogUiState.Loading)
    val searchResults = _searchResults.asStateFlow()

    private val _selectedTag = MutableStateFlow<Tag>(Tag.General)
    val selectedTag = _selectedTag.asStateFlow()

    private val _tagResults = MutableStateFlow<BlogUiState>(BlogUiState.Loading)
    val tagResults = _tagResults.asStateFlow()

    init {
        getTagResults()
    }

    fun search() {
        viewModelScope.launch {
            _searchResults.value = BlogUiState.Loading
            try {
                val results = newsRepository.searchNews(_searchQuery.value)
                _searchResults.value = BlogUiState.Success(results)
            } catch (e: Exception) {
                Log.e("SearchViewModel", e.toString())
                _searchResults.value =
                    BlogUiState.Error("Failed to get results for ${_searchQuery.value}")
            }
        }
    }

    fun setSelectedTag(tag: Tag) {
        _selectedTag.value = tag
    }

    fun getTagResults() {
        _tagResults.value = BlogUiState.Loading
        viewModelScope.launch {
            try {
                val results = newsRepository.getTagNews(_selectedTag.value.value)
                _tagResults.value = BlogUiState.Success(results)
            } catch (e: Exception) {
                Log.e("SearchViewModel", e.toString())
                _tagResults.value =
                    BlogUiState.Error("Failed to get results for ${_selectedTag.value.value}")
            }
        }
    }

}
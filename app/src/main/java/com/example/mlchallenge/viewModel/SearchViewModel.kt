package com.example.mlchallenge.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mlchallenge.data.repository.IArticleRepository
import com.example.mlchallenge.model.SearchHistory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: IArticleRepository) : ViewModel() {

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery


    private val _searchHistory = MutableLiveData<List<SearchHistory>>()
    val searchHistory: LiveData<List<SearchHistory>> = _searchHistory

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    // Este metodo guarda la búsqueda
    fun searchArticles(query: String) {
        _searchQuery.value = query
    }

    fun getSearchHistory() {
        viewModelScope.launch {
            repository.getSearchHistory()
                .collectLatest {
                    _searchHistory.value = it
                }
        }
    }

    fun deleteSearchHistoryById(id: Int) {
        viewModelScope.launch {
            repository.deleteHistoryById(id)
                getSearchHistory()
        }
    }

    fun deleteAllSearchHistory() {
        viewModelScope.launch {
            repository.deleteAllHistory()
                getSearchHistory()
        }
    }

}
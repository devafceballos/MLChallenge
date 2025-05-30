package com.example.mlchallenge.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mlchallenge.data.repository.IArticleRepository
import com.example.mlchallenge.model.Article
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ArticleListViewModel(
    private val repository: IArticleRepository
) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _fallbackTriggered = MutableLiveData<Boolean>()
    val fallbackTriggered: LiveData<Boolean> = _fallbackTriggered

    fun searchArticles(query: String) {
        viewModelScope.launch {
            repository.searchArticles(query)
                .collectLatest { results ->
                    _articles.value = results
                    _fallbackTriggered.value = results.isNotEmpty().not()
                    Log.d("ArticleVM", "Recibidos ${results.size} artículos")
                }
        }
    }

    fun retryLastSearch() {
        _articles.value?.firstOrNull()?.let {
            searchArticles(it.name)
        }
    }
}


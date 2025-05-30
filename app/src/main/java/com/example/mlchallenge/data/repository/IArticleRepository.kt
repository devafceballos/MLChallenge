package com.example.mlchallenge.data.repository

import com.example.mlchallenge.model.Article
import com.example.mlchallenge.model.ArticleDataResponse
import com.example.mlchallenge.model.SearchHistory
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {
    suspend fun searchArticles(query: String): Flow<List<Article>>
    suspend fun getSearchHistory(): Flow<List<SearchHistory>>
    suspend fun deleteHistoryById(id: Int)
    suspend fun deleteAllHistory()
}
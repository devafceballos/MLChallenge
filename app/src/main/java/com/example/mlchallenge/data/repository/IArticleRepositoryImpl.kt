package com.example.mlchallenge.data.repository

import com.example.mlchallenge.data.local.SearchHistoryDao
import com.example.mlchallenge.data.remote.ArticleApiService
import com.example.mlchallenge.model.Article
import com.example.mlchallenge.model.SearchHistory
import com.example.mlchallenge.model.toArticleList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class IArticleRepositoryImpl(
    private val api: ArticleApiService,
    private val searchHistoryDao: SearchHistoryDao
) : IArticleRepository {

    override suspend fun searchArticles(query: String): Flow<List<Article>> =
        flow {
            searchHistoryDao.insert(SearchHistory(query = query))
            val response = api.searchProducts(query = query)
            emit(response.toArticleList())

        }.catch {
            it
        }

    override suspend fun getSearchHistory(): Flow<List<SearchHistory>> =
        searchHistoryDao.getAllSearchQueries()


    override suspend fun deleteHistoryById(id: Int) {
        searchHistoryDao.deleteById(id)
    }

    override suspend fun deleteAllHistory() {
        searchHistoryDao.clearAll()
    }
}

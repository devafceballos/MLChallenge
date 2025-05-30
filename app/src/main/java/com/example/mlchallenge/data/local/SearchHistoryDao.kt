package com.example.mlchallenge.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mlchallenge.model.SearchHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Or IGNORE if you don't want to replace based on PK
    suspend fun insert(searchHistory: SearchHistory): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(searchHistories: List<SearchHistory>)

    @Update
    suspend fun update(searchHistory: SearchHistory)

    @Delete
    suspend fun delete(searchHistory: SearchHistory)

    @Query("DELETE FROM search_history WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM search_history WHERE `query` = :queryText") // Note backticks for "query"
    suspend fun deleteByQueryText(queryText: String)

    @Query("DELETE FROM search_history")
    suspend fun clearAll()

    @Query("SELECT * FROM search_history WHERE id = :id")
    fun getSearchHistoryById(id: Int): Flow<SearchHistory?>

    @Query("SELECT * FROM search_history ORDER BY id DESC")
    fun getAllSearchQueries(): Flow<List<SearchHistory>>

    @Query("SELECT DISTINCT `query` FROM search_history ORDER BY id DESC") // Order by last time it was searched
    fun getDistinctSearchQueries(): Flow<List<String>>

    @Query("SELECT * FROM search_history WHERE `query` LIKE '%' || :searchTerm || '%' ORDER BY id DESC")
    fun searchQueries(searchTerm: String): Flow<List<SearchHistory>>

    @Query("SELECT * FROM search_history ORDER BY id DESC LIMIT :limit")
    fun getRecentSearchQueries(limit: Int): Flow<List<SearchHistory>>

}
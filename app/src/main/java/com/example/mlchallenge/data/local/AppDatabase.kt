package com.example.mlchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mlchallenge.model.SearchHistory

@Database(entities = [SearchHistory::class], version = 2, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
}

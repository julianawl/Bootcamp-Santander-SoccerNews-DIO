package com.julianawl.soccernews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.julianawl.soccernews.domain.News

@Database(entities = [News::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDAO
}
package com.julianawl.soccernews.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.julianawl.soccernews.domain.News

const val dbName = "soccer-news"
@Database(entities = [News::class], version = 1)
abstract class SoccerNewsDb : RoomDatabase() {
    abstract fun newsDao(): NewsDAO
}
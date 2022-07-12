package com.julianawl.soccernews.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.julianawl.soccernews.domain.News

@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(news: News)

    @Query("SELECT * FROM news WHERE favorite = 1")
    suspend fun loadFavoriteNews(): List<News>
}
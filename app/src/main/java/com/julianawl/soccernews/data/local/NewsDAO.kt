package com.julianawl.soccernews.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.julianawl.soccernews.domain.News

@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(news: News)

    @Query("SELECT * FROM news WHERE favorite = 1")
    fun loadFavoriteNews(): LiveData<List<News>>
}
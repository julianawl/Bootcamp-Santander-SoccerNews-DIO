package com.julianawl.soccernews.repository

import com.julianawl.soccernews.data.local.NewsDAO
import com.julianawl.soccernews.data.local.SoccerNewsDb
import com.julianawl.soccernews.data.remote.SoccerNewsApi
import com.julianawl.soccernews.domain.News
import retrofit2.Call
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val dao: NewsDAO,
    private val remoteApi: SoccerNewsApi
) {

    fun loadAllNews(): Call<List<News>> {
        return remoteApi.getNews()
    }

    suspend fun saveNews(news: News) {
        dao.save(news)
    }

    suspend fun loadFavoriteNews(): List<News> {
        return dao.loadFavoriteNews()
    }
}
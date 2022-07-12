package com.julianawl.soccernews.repository

import com.julianawl.soccernews.data.local.SoccerNewsDb
import com.julianawl.soccernews.data.remote.AppRetrofit
import com.julianawl.soccernews.data.remote.SoccerNewsApi
import com.julianawl.soccernews.domain.News
import retrofit2.Call

class NewsRepository(
    db: SoccerNewsDb,
    private val remoteApi: SoccerNewsApi = AppRetrofit.create()
) {

    private val dao = db.newsDao()

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
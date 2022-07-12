package com.julianawl.soccernews.data.remote

import com.julianawl.soccernews.domain.News
import retrofit2.Call
import retrofit2.http.GET

interface SoccerNewsApi {

    @GET("news.json")
    fun getNews(): Call<List<News>>
}
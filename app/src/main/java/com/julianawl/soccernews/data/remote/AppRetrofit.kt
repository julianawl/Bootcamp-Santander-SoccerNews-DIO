package com.julianawl.soccernews.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://digitalinnovationone.github.io/soccer-news-api/"

class AppRetrofit {

    companion object {
        fun create(): SoccerNewsApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsApi::class.java)
        }
    }
}
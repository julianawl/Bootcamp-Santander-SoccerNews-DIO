package com.julianawl.soccernews.repository

import androidx.room.Room
import com.julianawl.soccernews.App
import com.julianawl.soccernews.data.local.SoccerNewsDb
import com.julianawl.soccernews.data.remote.SoccerNewsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository {
    var remoteApi: SoccerNewsApi? = null
    var localDb: SoccerNewsDb? = null

    init {
        remoteApi = Retrofit.Builder()
            .baseUrl(REMOTE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SoccerNewsApi::class.java)

        localDb =
            App().getInstance()
                ?.let { Room.databaseBuilder(it, SoccerNewsDb::class.java, LOCAL_DB_NAME).build() }
    }

    fun getInstance(): NewsRepository {
        return LazyHolder.INSTANCE
    }

    private object LazyHolder {
        val INSTANCE: NewsRepository = NewsRepository()
    }

    companion object {
        const val REMOTE_API_URL = "https://digitalinnovationone.github.io/soccer-news-api/"
        const val LOCAL_DB_NAME = "soccer-news"
    }
}
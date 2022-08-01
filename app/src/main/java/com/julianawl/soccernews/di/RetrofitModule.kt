package com.julianawl.soccernews.di

import com.julianawl.soccernews.data.remote.SoccerNewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://digitalinnovationone.github.io/soccer-news-api/"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    //libs externas
    @Singleton
    @Provides
    fun provideSoccerNewsApi(): SoccerNewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SoccerNewsApi::class.java)
    }
}
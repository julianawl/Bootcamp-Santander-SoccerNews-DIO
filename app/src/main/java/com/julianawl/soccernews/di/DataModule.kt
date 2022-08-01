package com.julianawl.soccernews.di

import com.julianawl.soccernews.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    //Para interfaces
//    @Singleton
//    @Binds
//    abstract fun bindNewsRepository(
//        newsRepository: NewsRepository
//    ): NewsRepositoryInterface
}
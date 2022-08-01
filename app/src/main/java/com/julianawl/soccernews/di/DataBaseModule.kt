package com.julianawl.soccernews.di

import android.content.Context
import androidx.room.Room
import com.julianawl.soccernews.data.local.NewsDAO
import com.julianawl.soccernews.data.local.SoccerNewsDb
import com.julianawl.soccernews.data.local.dbName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun provideNewsDao(db: SoccerNewsDb) : NewsDAO {
        return db.newsDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context):SoccerNewsDb{
        return Room.databaseBuilder(
            context.applicationContext,
            SoccerNewsDb::class.java,
            dbName
        ).build()
    }
}
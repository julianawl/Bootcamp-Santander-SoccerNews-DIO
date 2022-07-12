package com.julianawl.soccernews.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.julianawl.soccernews.domain.News
import com.julianawl.soccernews.repository.NewsRepository
import java.util.concurrent.Executors


class FavoritesViewModel : ViewModel() {
    fun loadFavoriteNews(): LiveData<List<News>>? {
        return NewsRepository().getInstance().localDb?.newsDao()?.loadFavoriteNews()
    }

    fun saveNews(news: News) {
        Executors.newSingleThreadExecutor().execute {
            NewsRepository().getInstance().localDb?.newsDao()?.save(news)
        }
    }
}
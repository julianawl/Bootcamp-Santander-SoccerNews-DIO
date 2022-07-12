package com.julianawl.soccernews.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julianawl.soccernews.domain.News
import com.julianawl.soccernews.repository.NewsRepository
import kotlinx.coroutines.launch


class FavoritesViewModel(private val repository: NewsRepository) : ViewModel() {

    private val favoriteNews : MutableLiveData<List<News>> = MutableLiveData()

    fun loadFavoriteNews() {
        viewModelScope.launch {
            favoriteNews.value = repository.loadFavoriteNews()
        }
    }

    fun saveNews(news: News) {
        viewModelScope.launch {
            repository.saveNews(news)
        }
    }

    fun getNews(): LiveData<List<News>> {
        return this.favoriteNews
    }
}
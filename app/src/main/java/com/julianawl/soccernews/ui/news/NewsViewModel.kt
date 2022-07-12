package com.julianawl.soccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julianawl.soccernews.domain.News
import com.julianawl.soccernews.repository.NewsRepository
import com.julianawl.soccernews.utils.State
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val news: MutableLiveData<List<News>> = MutableLiveData()
    private val state: MutableLiveData<State> = MutableLiveData()

    fun findNews() {
        state.value = State.DOING
        viewModelScope.launch {
            repository.loadAllNews().enqueue(object : Callback<List<News>> {
                override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                    if (response.isSuccessful) {
                        news.value = response.body()
                        state.value = State.DONE
                    } else {
                        state.value = State.ERROR
                    }
                }

                override fun onFailure(call: Call<List<News>>, t: Throwable) {
                    //remover printstacktrace
                    t.printStackTrace()
                    state.value = State.ERROR
                }
            })
        }
    }

    fun saveNews(news: News) {
        viewModelScope.launch {
            repository.saveNews(news)
        }
    }

    fun getNews(): LiveData<List<News>> {
        return this.news
    }

    fun getState(): LiveData<State> {
        return this.state
    }
}
package com.julianawl.soccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.julianawl.soccernews.domain.News
import com.julianawl.soccernews.repository.NewsRepository
import com.julianawl.soccernews.utils.State
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class NewsViewModel : ViewModel() {

    private val news: MutableLiveData<List<News>> = MutableLiveData()
    private val state: MutableLiveData<State> = MutableLiveData()

    fun findNews() {
        state.value = State.DOING
        NewsRepository().getInstance().remoteApi?.getNews()?.enqueue(object : Callback<List<News>> {
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

    fun saveNews(news: News) {
        //usar coroutines
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            NewsRepository().getInstance().localDb?.newsDao()?.save(news)
        }
    }

    fun getNews(): LiveData<List<News>> {
        return this.news
    }

    fun getState(): LiveData<State> {
        return this.state
    }
}
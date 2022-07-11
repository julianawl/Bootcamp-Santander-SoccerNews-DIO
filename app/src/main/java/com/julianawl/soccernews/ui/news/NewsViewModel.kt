package com.julianawl.soccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julianawl.soccernews.data.remote.SoccerNewsApi
import com.julianawl.soccernews.domain.News
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModel : ViewModel() {
    private val BASE_URL = "https://digitalinnovationone.github.io/soccer-news-api/"
    private val news: MutableLiveData<List<News>> = MutableLiveData()
    private var service: SoccerNewsApi
    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        service = retrofit.create(SoccerNewsApi::class.java)
        this.findNews()
    }

    fun findNews() {
        service.getNews().enqueue(object : Callback<List<News>>{
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if(response.isSuccessful){
                    news.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getNews(): LiveData<List<News>> {return this.news}
}
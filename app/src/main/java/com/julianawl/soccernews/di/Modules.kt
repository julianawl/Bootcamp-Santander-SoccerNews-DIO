package com.julianawl.soccernews.di

import com.julianawl.soccernews.data.local.SoccerNewsDb
import com.julianawl.soccernews.data.remote.AppRetrofit
import com.julianawl.soccernews.repository.NewsRepository
import com.julianawl.soccernews.ui.favorites.FavoritesViewModel
import com.julianawl.soccernews.ui.news.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { NewsViewModel(get())}
    viewModel { FavoritesViewModel(get()) }
}

val repositoryModule = module {
    single { NewsRepository(db = get()) }
}

val dataModule = module {
    single { SoccerNewsDb.getDatabase(context = androidContext())}
    single { AppRetrofit.create() }
}
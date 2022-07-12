package com.julianawl.soccernews

import android.app.Application
import com.julianawl.soccernews.di.dataModule
import com.julianawl.soccernews.di.repositoryModule
import com.julianawl.soccernews.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            koin.loadModules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    dataModule
                )
            )
        }
    }
}
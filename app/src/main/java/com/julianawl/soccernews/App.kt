package com.julianawl.soccernews

import android.app.Application

//Adicionar injeção de dependências
class App: Application() {

    private var instance: App? = null

    fun getInstance(): App? {
        return instance
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
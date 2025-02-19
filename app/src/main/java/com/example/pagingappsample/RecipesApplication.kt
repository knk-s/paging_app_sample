package com.example.pagingappsample

import android.app.Application
import com.example.pagingappsample.data.AppContainer
import com.example.pagingappsample.data.DefaultAppContainer

class RecipesApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}

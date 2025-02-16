package com.example.paging_app_sample

import android.app.Application
import com.example.paging_app_sample.data.AppContainer
import com.example.paging_app_sample.data.DefaultAppContainer

class RecipesApplication : Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
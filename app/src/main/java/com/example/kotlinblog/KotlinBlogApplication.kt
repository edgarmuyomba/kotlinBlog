package com.example.kotlinblog

import android.app.Application
import com.example.kotlinblog.data.AppContainer
import com.example.kotlinblog.data.DefaultAppContainer

class KotlinBlogApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}
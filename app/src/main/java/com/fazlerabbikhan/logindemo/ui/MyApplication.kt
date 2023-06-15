package com.fazlerabbikhan.logindemo.ui

import android.app.Application
import com.fazlerabbikhan.logindemo.di.AppComponent
import com.fazlerabbikhan.logindemo.di.AppModule
import com.fazlerabbikhan.logindemo.di.DaggerAppComponent

class MyApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
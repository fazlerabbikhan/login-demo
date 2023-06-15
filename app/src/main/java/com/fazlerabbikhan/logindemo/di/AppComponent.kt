package com.fazlerabbikhan.logindemo.di

import com.fazlerabbikhan.logindemo.ui.activity.LoginActivity
import com.fazlerabbikhan.logindemo.ui.activity.ProfileActivity
import com.fazlerabbikhan.logindemo.ui.activity.RegistrationActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: LoginActivity)
    fun inject(activity: RegistrationActivity)
    fun inject(activity: ProfileActivity)
}
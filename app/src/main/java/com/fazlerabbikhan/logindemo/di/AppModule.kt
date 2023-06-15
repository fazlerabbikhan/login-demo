package com.fazlerabbikhan.logindemo.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.fazlerabbikhan.logindemo.data.UserDao
import com.fazlerabbikhan.logindemo.data.UserDatabase
import com.fazlerabbikhan.logindemo.data.UserRepository
import com.fazlerabbikhan.logindemo.viewmodel.LoginViewModel
import com.fazlerabbikhan.logindemo.viewmodel.ProfileViewModel
import com.fazlerabbikhan.logindemo.viewmodel.RegistrationViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideAppDatabase(context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: UserDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

    @Provides
    fun provideLoginViewModel(userRepository: UserRepository): LoginViewModel {
        return LoginViewModel(userRepository)
    }

    @Provides
    fun provideRegistrationViewModel(userRepository: UserRepository): RegistrationViewModel {
        return RegistrationViewModel(userRepository)
    }

    @Provides
    fun provideProfileViewModel(userRepository: UserRepository, sharedPreferences: SharedPreferences): ProfileViewModel {
        return ProfileViewModel(userRepository, sharedPreferences)
    }
}
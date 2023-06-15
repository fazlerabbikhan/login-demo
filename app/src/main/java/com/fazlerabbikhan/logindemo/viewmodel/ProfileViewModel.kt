package com.fazlerabbikhan.logindemo.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fazlerabbikhan.logindemo.data.User
import com.fazlerabbikhan.logindemo.data.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _profileData = MutableLiveData<User?>()
    val profileData: MutableLiveData<User?> = _profileData

    fun getProfileData() {
        viewModelScope.launch {
            val loggedInUsername = sharedPreferences.getString(KEY_USERNAME, null)
            if (!loggedInUsername.isNullOrEmpty()) {
                val user = userRepository.getUserByUsername(loggedInUsername)
                _profileData.value = user
            }
        }
    }

    companion object {
        private const val KEY_USERNAME = "username"
    }
}


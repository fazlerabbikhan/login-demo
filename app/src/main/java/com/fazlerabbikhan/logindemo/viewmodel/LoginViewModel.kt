package com.fazlerabbikhan.logindemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fazlerabbikhan.logindemo.data.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUser(username, password)
            _loginResult.value = (user != null) && (user.password == password)
        }
    }
}


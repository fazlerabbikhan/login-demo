package com.fazlerabbikhan.logindemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fazlerabbikhan.logindemo.data.User
import com.fazlerabbikhan.logindemo.data.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _registrationResult = MutableLiveData<Boolean>()
    val registrationResult: LiveData<Boolean> = _registrationResult

    fun register(username: String, email: String, mobile: String, password: String) {
        viewModelScope.launch {
            val user = User(username = username, email = email, mobile = mobile, password = password)
            userRepository.insertUser(user)
            _registrationResult.value = true
        }
    }
}

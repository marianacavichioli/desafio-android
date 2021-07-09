package com.picpay.desafio.android.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val error = MutableLiveData<Boolean>(false)
    val users: LiveData<List<User>> = userRepository.users

    init {
        viewModelScope.launch {
            loadUsers()
        }
    }

    private suspend fun loadUsers() {
        try {
            userRepository.getUsers()
            error.value = false
        } catch (exception: Exception) {
            error.value = true
        }
    }
}
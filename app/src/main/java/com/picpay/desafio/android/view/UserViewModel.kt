package com.picpay.desafio.android.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.picpay.desafio.android.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class UserViewModel(userRepository: UserRepository) : ViewModel() {
    val users = userRepository.getUsers().asLiveData()
}
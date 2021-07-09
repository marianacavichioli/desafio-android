package com.picpay.desafio.android.repository

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.UserDao
import com.picpay.desafio.android.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class UserRepository(private val userDao: UserDao) {
    val users: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun getUsers() {
        withContext(Dispatchers.IO) {
            val users = RetrofitService.getService().getUsers().await()
            userDao.insertUsers(users)
        }
    }
}
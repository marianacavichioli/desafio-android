package com.picpay.desafio.android.repository

import androidx.room.withTransaction
import com.picpay.desafio.android.data.UserDatabase
import com.picpay.desafio.android.network.RetrofitService
import com.picpay.desafio.android.utils.networkBoundResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.await

open class UserRepository(private val userDatabase: UserDatabase) {
    private val userDao = userDatabase.userDao()

    @ExperimentalCoroutinesApi
    open fun getUsers() = networkBoundResource(
        query = {
            userDao.getAllUsers()
        },
        fetch = {
            RetrofitService.getService().getUsers()
        },
        saveFetchResult = { call ->
            userDatabase.withTransaction {
                userDao.deleteUsers()
                userDao.insertUsers(call.await())
            }
        }
    )
}
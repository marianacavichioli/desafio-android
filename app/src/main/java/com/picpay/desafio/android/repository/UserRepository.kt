package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.network.RetrofitService
import retrofit2.Call

class UserRepository {
    fun getUsers(): Call<List<User>> {
        return RetrofitService.getService().getUsers()
    }
}
package com.picpay.desafio.android.di

import com.picpay.desafio.android.network.RetrofitService
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.view.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userViewModel = module(override = true) {
    factory { RetrofitService.getService() }
    factory { UserRepository() }
    viewModel {
        MainActivityViewModel(userRepository = get())
    }
}
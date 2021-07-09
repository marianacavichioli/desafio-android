package com.picpay.desafio.android.di

import android.app.Application
import androidx.room.Room
import com.picpay.desafio.android.data.UserDao
import com.picpay.desafio.android.data.UserDatabase
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.view.UserViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userViewModel = module {
    factory { UserRepository(get()) }
    viewModel {
        UserViewModel(get())
    }
}

val databaseModule = module {

    fun provideDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(application, UserDatabase::class.java, "user_table")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideUsersDao(database: UserDatabase): UserDao {
        return database.userDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideUsersDao(get()) }
}
package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.databaseModule
import com.picpay.desafio.android.di.userViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)

            modules(listOf(userViewModel, databaseModule))
        }
    }

}
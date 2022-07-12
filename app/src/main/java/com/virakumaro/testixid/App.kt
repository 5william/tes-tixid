package com.virakumaro.testixid

import android.app.Application
import com.virakumaro.testixid.di.dataSourceModule
import com.virakumaro.testixid.di.networkModule
import com.virakumaro.testixid.di.repositoryModule
import com.virakumaro.testixid.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataSourceModule, networkModule, repositoryModule, viewModelModule)
        }
    }
}
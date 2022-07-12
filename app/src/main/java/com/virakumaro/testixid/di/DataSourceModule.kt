package com.virakumaro.testixid.di

import com.virakumaro.testixid.data.remote.UserDataSource
import com.virakumaro.testixid.data.remote.UserDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<UserDataSource> {UserDataSourceImpl(service = get())}
}
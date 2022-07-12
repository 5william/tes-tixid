package com.virakumaro.testixid.di

import com.virakumaro.testixid.data.repository.UserRepository
import com.virakumaro.testixid.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(userDatasource = get()) }
}
package com.virakumaro.testixid.data.repository

import com.virakumaro.testixid.data.remote.UserDataSource

class UserRepositoryImpl(val userDatasource: UserDataSource): UserRepository {
    override fun getUsers(since: Long) = userDatasource.getUsers(since)
}
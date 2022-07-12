package com.virakumaro.testixid.data.remote

import com.virakumaro.testixid.data.Resource
import com.virakumaro.testixid.model.User
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun getUsers(since: Long): Flow<Resource<List<User>>>
}
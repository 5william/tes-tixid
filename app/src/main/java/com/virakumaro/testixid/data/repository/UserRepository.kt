package com.virakumaro.testixid.data.repository



import androidx.lifecycle.LiveData
import com.virakumaro.testixid.data.Resource
import com.virakumaro.testixid.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
     fun getUsers(since: Long): Flow<Resource<List<User>>>
 }



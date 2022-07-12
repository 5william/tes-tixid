package com.virakumaro.testixid.data.remote

import com.virakumaro.testixid.data.Resource
import com.virakumaro.testixid.data.service.UserService
import com.virakumaro.testixid.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

class UserDataSourceImpl(private val service: UserService): UserDataSource {
    override fun getUsers(since: Long): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(service.getUsers(since)))
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    when {
                        e.code() == 401 -> {
                            emit(Resource.Error("Unauthorized"))
                        }
                        e.code() == 403 -> {
                            emit(Resource.Error("Server limit reached"))
                        }
                        else -> {
                            emit(Resource.Error("Something wrong"))
                        }
                    }
                }

                is UnknownHostException -> {
                    emit(Resource.Error("No Internet"))
                }

                else -> {
                    emit(Resource.Error("Something wrong"))
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}
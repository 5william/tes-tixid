package com.virakumaro.testixid.data.service

import com.virakumaro.testixid.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface UserService {
    @GET("users")
    suspend fun getUsers(
        @Query("since") since : Long, @Query("per_page") perPage : Int = 10
    ): List<User>

}
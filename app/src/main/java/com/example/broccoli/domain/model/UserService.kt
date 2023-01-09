package com.example.broccoli.domain.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/fakeAuth")
    suspend fun createUser(@Body user: Unit?): Response<Unit>
}
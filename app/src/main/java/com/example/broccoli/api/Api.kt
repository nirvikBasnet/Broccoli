package com.example.broccoli.api

import com.example.broccoli.domain.model.ResponseMessage
import com.example.broccoli.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("/fakeAuth")
    suspend fun sendUser(
        @Body user: User
    ): Response<String>
}
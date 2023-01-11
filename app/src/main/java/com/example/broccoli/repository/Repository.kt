package com.example.broccoli.repository

import com.example.broccoli.api.RetrofitInstance
import com.example.broccoli.domain.model.ResponseMessage
import com.example.broccoli.domain.model.User
import retrofit2.Response

class Repository {
    suspend fun sendUser(user: User): Response<String> {
        return RetrofitInstance.api.sendUser(user)
    }
}
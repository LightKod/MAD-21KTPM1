package com.example.mad_21ktpm1_group11.api

import com.example.mad_21ktpm1_group11.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface AccountApi {
    @GET("account")
    fun getUserDetail(@Header("Authorization") authToken: String): Call<User>
}
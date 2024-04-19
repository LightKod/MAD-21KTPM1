package com.example.mad_21ktpm1_group11.api

import com.example.mad_21ktpm1_group11.models.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import com.google.gson.JsonObject

interface AuthService {
    @POST("auth/login")
    fun authenticate(@Body request: JsonObject): Call<AuthResponse>
    @POST("auth/register")
    fun register(@Body request: JsonObject): Call<ResponseBody>
}

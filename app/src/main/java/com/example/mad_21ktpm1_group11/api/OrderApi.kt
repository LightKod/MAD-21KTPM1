package com.example.mad_21ktpm1_group11.api

import com.example.mad_21ktpm1_group11.models.Order
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface OrderApi {
    @POST("order/new")
    fun uploadOrder(
        @Body order: Order
    ): Call<ResponseBody>

    @GET("order/{id}")
    fun getOrderById(@Path("id") id: Int): Call<Order>
    @POST("order/update")
    fun updateOrderById(@Header("Authorization") authToken: String,@Body request: JsonObject ): Call<Order>

}
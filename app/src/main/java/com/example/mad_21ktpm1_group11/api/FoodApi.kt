package com.example.mad_21ktpm1_group11.api

import com.example.mad_21ktpm1_group11.models.Food
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodApi {
    @GET("food")
    fun getAllFood(): Call<List<Food>>

    @GET("food/{id}")
    fun getFoodById(@Path("id") id: Int): Call<Food>
}
package com.example.mad_21ktpm1_group11.api

import com.example.mad_21ktpm1_group11.models.Genre
import com.example.mad_21ktpm1_group11.models.Room
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomApi {
    @GET("room/{id}")
    fun GetRoomById(@Path("id") id: Int): Call<Room>
}
package com.example.mad_21ktpm1_group11.api

import com.example.mad_21ktpm1_group11.models.Cinema
import retrofit2.Call
import retrofit2.http.GET

interface CinemaApi {
    @GET("cinemas")
    fun getAllCinemas(): Call<List<Cinema>>
}
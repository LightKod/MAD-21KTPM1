package com.example.mad_21ktpm1_group11.api

import com.example.mad_21ktpm1_group11.models.Cinema
import com.example.mad_21ktpm1_group11.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CinemaApi {
    @GET("cinemas")
    fun getAllCinemas(): Call<List<Cinema>>

    @GET("cinemas/{id}")
    fun getCinemaById(@Path("id") id: Int): Call<Cinema>
}
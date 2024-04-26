package com.example.mad_21ktpm1_group11.api

import com.example.mad_21ktpm1_group11.models.Genre
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GenreApi {
    @GET("genre")
    fun getAllGenres(): Call<List<Genre>>

    @GET("genre/{id}")
    fun getGenreByID(@Path("id") id: Int): Call<Genre>
}
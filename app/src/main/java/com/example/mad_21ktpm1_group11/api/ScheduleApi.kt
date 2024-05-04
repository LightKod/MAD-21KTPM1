package com.example.mad_21ktpm1_group11.api

import com.example.mad_21ktpm1_group11.models.CinemaSchedule
import com.example.mad_21ktpm1_group11.models.Schedule
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApi {
    @GET("schedule/{id}")
    fun GetScheduleByID(@Path("id") id: Int): Call<Schedule>

    @GET("schedule")
    fun GetAllSchedule(): Call<List<Schedule>>

    @GET("schedule/{id}/tickets")
    fun GetScheduleTickets(@Path("id") id: Int): Call<List<String>>

    @GET("schedule/by-date-movie-cinema")
    fun getSchedulesByDateMovieCinema(
        @Query("date") date: String?,
        @Query("movieId") movieId: Int
    ): Call<List<CinemaSchedule>>
}

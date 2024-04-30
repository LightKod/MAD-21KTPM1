package com.example.mad_21ktpm1_group11.api

import com.example.mad_21ktpm1_group11.models.Movie
import com.example.mad_21ktpm1_group11.models.Schedule
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleApi {
    @GET("schedule/{id}")
    fun GetScheduleByID(@Path("id") id: Int): Call<Schedule>
}

package com.example.mad_21ktpm1_group11.api;

import com.example.mad_21ktpm1_group11.models.CinemaSchedule;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface ScheduleApi {
    @GET("schedule/by-date-movie-cinema")
    Call<List<CinemaSchedule>> getSchedulesByDateMovieCinema(
            @Query("date") String date,
            @Query("movieId") int movieId
    );
}

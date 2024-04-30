package com.example.mad_21ktpm1_group11.models

import com.google.gson.annotations.SerializedName

data class Schedule (
    @SerializedName("id") val id: Int,
    @SerializedName("schedule_id") val schedule_id: Int,
    @SerializedName("movie_id") val movie_id: Int,
    @SerializedName("room_id") val room_id: Int,
    @SerializedName("cinema_id") val cinema_id: Int,
    @SerializedName("schedule_date") val schedule_date: Long,
    @SerializedName("schedule_start") val schedule_start: String,
    @SerializedName("booked_seats") val booked_seats: List<Int>,
) {
}
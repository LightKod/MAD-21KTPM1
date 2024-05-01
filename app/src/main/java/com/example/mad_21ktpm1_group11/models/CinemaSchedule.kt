package com.example.mad_21ktpm1_group11.models

data class CinemaSchedule(
    val id: String,
    val cinemaId: Int,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val schedules: List<Schedule>
)

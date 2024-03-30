package com.example.mad_21ktpm1_group11.models

data class Movie(
    val id: Int,
    val name: String,
    val classification: String,
    val premiereDate: String,
    val duration: Int,
    val director: String,
    val poster: String
) {
}
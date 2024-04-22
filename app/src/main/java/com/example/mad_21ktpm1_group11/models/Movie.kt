package com.example.mad_21ktpm1_group11.models

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("original_title") val name: String,
    val classification: String,
    @SerializedName("release_date") val premiereDate: String,
    @SerializedName("runTime") val duration: Int,
    val director: String,
    @SerializedName("poster_path") val poster: String,
    /*val gerne_ids: Array<Int>,
    val overview: String,
    val popularity: Float,
    val actors: Array<Int>,*/

) {
}
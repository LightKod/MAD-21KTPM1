package com.example.mad_21ktpm1_group11.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("original_title") val name: String,
    val classification: String,
    @SerializedName("release_date") val premiereDate: String,
    @SerializedName("runTime") val duration: Int,
    @SerializedName("director") val director: Int,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Float,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("actors") val actors: List<Int>
) {
}
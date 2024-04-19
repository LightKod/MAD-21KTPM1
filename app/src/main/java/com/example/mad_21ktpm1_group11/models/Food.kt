package com.example.mad_21ktpm1_group11.models

import com.google.gson.annotations.SerializedName

data class Food(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val poster: String,
) {
}
//data class CatFactResponse(
//    @SerializedName("fact") val fact: String,
//    @SerializedName("length") val length: Int
//)
package com.example.mad_21ktpm1_group11.models

import com.google.gson.annotations.SerializedName

data class Cinema (
    @SerializedName("cinema_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
) {
}
package com.example.mad_21ktpm1_group11.models

import com.google.gson.annotations.SerializedName

data class Room (
    @SerializedName("id") val id: Int,
    @SerializedName("room_id") val room_id: Int,
    @SerializedName("rows") val rows: Int,
    @SerializedName("columns") val columns: Int,
    @SerializedName("seats") val seats: List<Int>,
) {
}
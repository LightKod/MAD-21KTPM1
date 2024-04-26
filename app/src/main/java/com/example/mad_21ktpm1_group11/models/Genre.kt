package com.example.mad_21ktpm1_group11.models

import com.google.gson.annotations.SerializedName

data class Genre (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
) {
}
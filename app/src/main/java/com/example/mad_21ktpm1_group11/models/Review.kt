package com.example.mad_21ktpm1_group11.models

data class Review(
    var userId: Int,
    var userName: String,
    var reviewStar: Int,
    var review: String,
) {
}
package com.example.mad_21ktpm1_group11.models

data class User(
    val id: String,
    val googleId: String?,
    val name: String?,
    val avatar: String?,
    val gender: String?,
    val phone: String?,
    val email: String,
    val password: String?,
    val address: List<String>?,
    val dob: String?,
    val role: String?,
    val createdAt: String?,
    val isBanned: Boolean?,
    val emailVerified: Boolean?,
    val emailVerificationToken: String?,
    val emailVerificationExpires: Long?
)


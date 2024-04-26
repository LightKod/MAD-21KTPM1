package com.example.mad_21ktpm1_group11.api

import com.example.mad_21ktpm1_group11.models.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
interface PersonApi {
    @GET("person")
    fun getAllPeople(): Call<List<Person>>

    @GET("person/{id}")
    fun getPersonByID(@Path("id") id: Int): Call<Person>
}
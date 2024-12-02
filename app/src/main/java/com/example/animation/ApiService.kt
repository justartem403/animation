package com.example.animation

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}

val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").
addConverterFactory(GsonConverterFactory.create()).build().create(UserApiService::class.java)
package com.example.hiltpractice

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page:Int
    ): Response<UserResponse>

    @GET("movielist.json")
    suspend fun getMovies() : List<Movie>
}
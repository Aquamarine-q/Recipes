package com.example.recipes.data.api

import com.example.recipes.data.model.Recipes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/random")
    suspend fun getRecipes(
        @Header("x-api-key") apiKey: String,
        @Query("number") number: Int
    ): Response<Recipes>
}
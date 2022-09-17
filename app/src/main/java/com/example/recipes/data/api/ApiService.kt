package com.example.recipes.data.api

import com.example.recipes.data.model.Recipe
import com.example.recipes.data.model.Recipes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/random")
    suspend fun getRecipes(
        @Header("x-api-key") apiKey: String,
        @Query("number") number: Int
    ): Response<Recipes>

    @GET("recipes/{id}/information")
    suspend fun getRecipeDetails(
        @Header("x-api-key") apiKey: String,
        @Path("id") id: Int
    ): Response<Recipe>

    @GET("recipes/complexSearch")
    suspend fun getMostPopularRecipe(
        @Header("x-api-key") apiKey: String,
        @Query("number") number: Int,
        @Query("sort") sort: String
    ): Response<Recipes>
}
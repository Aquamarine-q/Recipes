package com.example.recipes.data.api

import com.example.recipes.data.model.Recipes
import retrofit2.Response

interface ApiHelper {
    suspend fun getRecipes(): Response<Recipes>
}
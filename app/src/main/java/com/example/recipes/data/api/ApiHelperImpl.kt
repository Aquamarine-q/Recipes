package com.example.recipes.data.api

import com.example.recipes.data.model.Recipes
import retrofit2.Response

const val apiKey = "cf4b18993375468b913c5c0a67573d39"

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getRecipes(): Response<Recipes> = apiService.getRecipes(apiKey, 100)
}
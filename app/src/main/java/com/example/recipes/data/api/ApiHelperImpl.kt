package com.example.recipes.data.api

import com.example.recipes.data.model.Recipes
import retrofit2.Response

const val apiKey = "cf4b18993375468b913c5c0a67573d39"

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getRecipes(): Response<Recipes> =
        apiService.getRecipes(apiKey, 10)

    //override suspend fun getRecipeDetails(): Response<Recipe> =
        //apiService.getRecipeDetails(apiKey/*,*/)


    override suspend fun getMostPopularRecipe(): Response<Recipes> =
        apiService.getMostPopularRecipe(apiKey, 1,"popularity")

}
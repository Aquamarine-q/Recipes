package com.example.recipes.data.repository

import com.example.recipes.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getRecipes() = apiHelper.getRecipes()
}
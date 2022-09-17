package com.example.recipes.data

import com.example.recipes.data.model.Recipes
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ApiInterface {
    @GET("recipes/random")
    fun getRecipes(@Header("x-api-key") apiKey: String, @Query("number") number: Int) : Call<Recipes>

    companion object {

        private var BASE_URL = "https://api.spoonacular.com/"

        fun create() : ApiInterface {

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}
package com.example.recipes.data.model

data class Equipment (
    val id: Int,
    val name: String,
    val localizedName: String,
    val image: String,
    val temperature: Temperature
)
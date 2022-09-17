package com.example.recipes.data.model

data class Step(
    val number: Int,
    val step: String,
    val ingredients: List<Ingredient>,
    val equipment: List<Equipment>,
    val length: Length
)


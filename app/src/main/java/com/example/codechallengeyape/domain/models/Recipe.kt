package com.example.codechallengeyape.domain.models

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val difficulty: String,
    val cuisine: String,
    val tags: List<String>,
    val image: String,
    val rating: Double,
    val mealType: List<String>,
    val location: Location
)
package com.example.codechallengeyape.data.network.api

import com.example.codechallengeyape.domain.models.Recipe
import retrofit2.Response
import retrofit2.http.GET

interface RecipeService {
    @GET("recipes")
    suspend fun getRecipes(): Response<List<Recipe>>
}
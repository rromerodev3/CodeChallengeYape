package com.example.codechallengeyape.data.network.api

import com.example.codechallengeyape.domain.models.Recipe
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface RecipeService {
    @GET("recipes")
    suspend fun getRecipes(): Response<List<Recipe>>

    @GET("recipes")
    suspend fun getResponseRecipes(): Response<ResponseBody>

    @GET("recipe")
    suspend fun getRecipe(): Response<Recipe>

    @GET("recipe")
    suspend fun getResponseRecipe(): Response<ResponseBody>
}
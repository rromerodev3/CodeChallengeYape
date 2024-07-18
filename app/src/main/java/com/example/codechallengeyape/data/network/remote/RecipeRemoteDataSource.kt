package com.example.codechallengeyape.data.network.remote

import com.example.codechallengeyape.data.network.ResultData
import com.example.codechallengeyape.data.network.api.RecipeService
import com.example.codechallengeyape.domain.interfaces.RecipeDataSource
import com.example.codechallengeyape.domain.models.Recipe
import javax.inject.Inject

class RecipeRemoteDataSource @Inject constructor(
    private val recipeService: RecipeService
): BaseRemoteDataSource(), RecipeDataSource {
    override suspend fun getRecipes(): ResultData<List<Recipe>> {
        return safeApiCall("Error getting recipes") {
            recipeService.getRecipes()
        }
    }

    override suspend fun getRecipe(): ResultData<Recipe> {
        return safeApiCall("Error getting recipe") {
            recipeService.getRecipe()
        }
    }
}
package com.example.codechallengeyape.data.repositories

import com.example.codechallengeyape.data.network.ResultData
import com.example.codechallengeyape.data.network.remote.RecipeRemoteDataSource
import com.example.codechallengeyape.domain.interfaces.RecipeDataSource
import com.example.codechallengeyape.domain.models.Recipe
import javax.inject.Inject

class RecipeRepository  @Inject constructor(
    private val remoteDataSource: RecipeRemoteDataSource
): RecipeDataSource {
    override suspend fun getRecipes(): ResultData<List<Recipe>> {
        return remoteDataSource.getRecipes()
    }
}
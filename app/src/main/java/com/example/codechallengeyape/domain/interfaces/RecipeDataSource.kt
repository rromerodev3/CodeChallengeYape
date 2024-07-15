package com.example.codechallengeyape.domain.interfaces

import com.example.codechallengeyape.data.network.ResultData
import com.example.codechallengeyape.domain.models.Recipe

interface RecipeDataSource {
    suspend fun getRecipes(): ResultData<List<Recipe>>
}
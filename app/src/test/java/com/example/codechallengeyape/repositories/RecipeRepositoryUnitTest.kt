package com.example.codechallengeyape.repositories

import com.example.codechallengeyape.data.network.ResultData
import com.example.codechallengeyape.data.repositories.RecipeRepository
import com.example.codechallengeyape.remote.FakeRecipeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RecipeRepositoryUnitTest {

    private val fakeRecipeDataSource = FakeRecipeDataSource()
    private val recipeRepository = RecipeRepository(fakeRecipeDataSource)

    @Test
    fun `test recipe repository get recipes is success`() = runTest {
        val recipeResponse = recipeRepository.getRecipes()

        assert(recipeResponse is ResultData.Success)
    }

    @Test
    fun `test recipe repository get recipes has elements`() = runTest {
        val recipesResultData = recipeRepository.getRecipes()
        val recipes = (recipesResultData as ResultData.Success).data

        assert(recipes.isNotEmpty())
    }

    @Test
    fun `test recipe repository get recipes correct amount`() = runTest {
        val recipesResultData = recipeRepository.getRecipes()
        val recipes = (recipesResultData as ResultData.Success).data

        assertEquals(5, recipes.size)
    }

    @Test
    fun `test recipe repository get recipe is success`() = runTest {
        val recipeResponse = recipeRepository.getRecipes()

        assert(recipeResponse is ResultData.Success)
    }
}
package com.example.codechallengeyape.repositories

import com.example.codechallengeyape.data.network.ResultData
import com.example.codechallengeyape.data.repositories.RecipeRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class RecipeRepositoryInstrumentedTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var recipeRepository: RecipeRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testRecipeRepositoryGetRecipesIsSuccess() = runTest {
        val recipesResultData = recipeRepository.getRecipes()

        assert(recipesResultData is ResultData.Success)
    }

    @Test
    fun testRecipeRepositoryGetRecipesHasElements() = runTest {
        val recipesResultData = recipeRepository.getRecipes()
        val recipes = (recipesResultData as ResultData.Success).data

        assert(recipes.isNotEmpty())
    }

    @Test
    fun testRecipeRepositoryGetRecipesCorrectAmount() = runTest {
        val recipesResultData = recipeRepository.getRecipes()
        val recipes = (recipesResultData as ResultData.Success).data

        assertEquals(25, recipes.size)
    }

    @Test
    fun testRecipeRepositoryGetRecipeIsSuccess() = runTest {
        val recipeResultData = recipeRepository.getRecipe()

        assert(recipeResultData is ResultData.Success)
    }
}
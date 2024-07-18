package com.example.codechallengeyape.network

import com.example.codechallengeyape.data.network.api.RecipeService
import com.example.codechallengeyape.domain.models.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ServicesInstrumentedTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var recipeService: RecipeService

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testRecipesEndpointRespondsOk() = runTest {
        val response = recipeService.getRecipes()
        assertEquals(200, response.code())
    }

    @Test
    fun testRecipesEndpointResponseIsValidJson() = runTest {
        val response = recipeService.getResponseRecipes()
        val jsonString = response.body()?.string()

        val responseType = object : TypeToken<List<Recipe>>(){}.type
        val recipes: List<Recipe> = Gson().fromJson(jsonString, responseType)
    }

    @Test
    fun testRecipeEndpointRespondsOk() = runTest {
        val response = recipeService.getRecipe()

        assertEquals(200, response.code())
    }

    @Test
    fun testRecipeEndpointResponseIsValidJson() = runTest {
        val response = recipeService.getResponseRecipe()
        val jsonString = response.body()?.string()

        Gson().fromJson(jsonString, Recipe::class.java)
    }
}
package com.example.codechallengeyape.framework.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codechallengeyape.data.network.ResultData
import com.example.codechallengeyape.data.repositories.RecipeRepository
import com.example.codechallengeyape.domain.models.Recipe
import com.example.codechallengeyape.presentation.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _currentScreen = MutableLiveData(MainActivity.Screens.Home)
    val currentScreen: LiveData<MainActivity.Screens> get() = _currentScreen

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _recipes = MutableLiveData<ResultData<List<Recipe>>>()
    val recipes: LiveData<ResultData<List<Recipe>>> get() = _recipes

    private val _selectedRecipe = MutableLiveData<Recipe>()
    val selectedRecipe: LiveData<Recipe> get() = _selectedRecipe

    fun getRecipes() {
        viewModelScope.launch {
            _isLoading.postValue(true)

            //simulate network delay
            delay(500)

            val recipes = recipeRepository.getRecipes()
            _recipes.postValue(recipes)

            _isLoading.postValue(false)
        }
    }

    fun goToScreen(newScreen: MainActivity.Screens) {
        _currentScreen.postValue(newScreen)
    }

    fun updateSelectedRecipe(recipe: Recipe) {
        _selectedRecipe.postValue(recipe)
    }
}
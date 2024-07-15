package com.example.codechallengeyape.framework.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codechallengeyape.data.network.ResultData
import com.example.codechallengeyape.data.repositories.RecipeRepository
import com.example.codechallengeyape.domain.models.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _recipes = MutableLiveData<ResultData<List<Recipe>>>()
    val recipes: LiveData<ResultData<List<Recipe>>> get() = _recipes

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
}
package com.example.codechallengeyape.presentation.home

import android.annotation.SuppressLint
import android.widget.Filter
import com.example.codechallengeyape.domain.models.Recipe
import java.util.Locale

class RecipesFilter(
    private val adapter: RecipesAdapter,
    private val initialRecipesList: List<Recipe>,
    private val showingRecipesList: MutableList<Recipe>
): Filter()  {

    override fun performFiltering(serachingText: CharSequence?): FilterResults {
        val filteredResults = FilterResults()
        val locale = Locale.getDefault()

        if (serachingText.isNullOrEmpty()) {
            filteredResults.apply {
                count = initialRecipesList.size
                values = initialRecipesList
            }
        } else {
            val filteredRecipes = mutableListOf<Recipe>()
            val query = serachingText.toString().trim().uppercase(locale).split(" ")

            for (recipe in initialRecipesList) {
                val isMatchRecipe = query.any { searchTerm ->
                    recipe.name.uppercase(locale).contains(searchTerm)
                }

                if (isMatchRecipe) {
                    filteredRecipes.add(recipe)
                }
            }

            filteredResults.apply {
                count = filteredRecipes.size
                values = filteredRecipes
            }
        }

        return filteredResults
    }

    @SuppressLint("NotifyDataSetChanged") @Suppress("UNCHECKED_CAST")
    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        showingRecipesList.clear()
        showingRecipesList.addAll(results!!.values as Collection<Recipe>)
        adapter.notifyDataSetChanged()
    }
}
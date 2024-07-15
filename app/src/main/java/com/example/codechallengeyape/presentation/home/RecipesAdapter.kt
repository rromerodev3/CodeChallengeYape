package com.example.codechallengeyape.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallengeyape.databinding.RecipeItemBinding
import com.example.codechallengeyape.domain.models.Recipe

class RecipesAdapter(
    private val recipes: List<Recipe>,
    private val recipeCallback: RecipeCallback
): RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    interface RecipeCallback {
        fun onRecipeSelected(recipe: Recipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = RecipeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe, recipeCallback)
        recipeCallback
    }

    override fun getItemCount() = recipes.size

    class RecipeViewHolder(
        private val binding: RecipeItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe, recipeCallback: RecipeCallback) {
            binding.recipe = recipe
            binding.root.setOnClickListener {
                recipeCallback.onRecipeSelected(recipe)
            }
        }
    }
}
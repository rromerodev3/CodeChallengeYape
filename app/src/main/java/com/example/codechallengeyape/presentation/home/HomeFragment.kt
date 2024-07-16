package com.example.codechallengeyape.presentation.home

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.codechallengeyape.data.network.ResultData
import com.example.codechallengeyape.databinding.FragmentHomeBinding
import com.example.codechallengeyape.domain.models.Recipe
import com.example.codechallengeyape.framework.viewModels.MainViewModel
import com.example.codechallengeyape.presentation.MainActivity
import com.example.codechallengeyape.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment(), RecipesAdapter.RecipeCallback {

    private lateinit var binding: FragmentHomeBinding
    private val parentViewModel: MainViewModel by activityViewModels()

    override fun createBinding(container: ViewGroup?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun setupUiBehavior() {
        parentViewModel.getRecipes()
    }

    override fun subscribeToEvents() {

        parentViewModel.isLoading.observe(viewLifecycleOwner) { showLoading ->
            binding.loading = showLoading
        }

        parentViewModel.recipes.observe(viewLifecycleOwner) {
            when(it) {
                is ResultData.Success -> {
                    setupRecyclerView(it.data)
                }
                is ResultData.Error -> {
                    Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupRecyclerView(recipes: List<Recipe>) {
        binding.rcVwRecipes.apply {
            setHasFixedSize(true)
            adapter = RecipesAdapter(recipes, this@HomeFragment)
        }
    }

    override fun onRecipeSelected(recipe: Recipe) {
        parentViewModel.updateSelectedRecipe(recipe)
        parentViewModel.goToScreen(MainActivity.Screens.Details)
    }
}
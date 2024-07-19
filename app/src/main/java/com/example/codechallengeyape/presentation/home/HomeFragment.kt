package com.example.codechallengeyape.presentation.home

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import com.example.codechallengeyape.R
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

    private lateinit var recipesAdapter: RecipesAdapter

    override fun createBinding(container: ViewGroup?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun setupUiBehavior() {
        // do nothing
    }

    fun filterRecipes(query: String?) {
        query?.let { searchTerm ->
            recipesAdapter.filter.filter(searchTerm)
        }
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

        if (parentViewModel.recipes.value == null) {
            parentViewModel.getRecipes()
        }
    }

    override fun getScreenTitle() = R.string.home_title

    override fun configureMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_action_bar, menu)
                val searchItem = menu.findItem(R.id.actionSearch)
                val searchView = searchItem?.actionView as SearchView

                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        filterRecipes(query)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        filterRecipes(newText)
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // do nothing
                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupRecyclerView(recipes: List<Recipe>) {
        recipesAdapter = RecipesAdapter(recipes, this@HomeFragment)
        binding.rcVwRecipes.apply {
            setHasFixedSize(true)
            adapter = recipesAdapter
        }
    }

    override fun onRecipeSelected(recipe: Recipe) {
        parentViewModel.updateSelectedRecipe(recipe)
        parentViewModel.goToScreen(MainActivity.Screens.Details)
    }
}
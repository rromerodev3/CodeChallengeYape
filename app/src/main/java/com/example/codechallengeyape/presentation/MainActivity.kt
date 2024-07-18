package com.example.codechallengeyape.presentation

import android.view.View
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.codechallengeyape.databinding.ActivityMainBinding
import com.example.codechallengeyape.framework.viewModels.MainViewModel
import com.example.codechallengeyape.presentation.base.BaseActivity
import com.example.codechallengeyape.presentation.detail.DetailsFragment
import com.example.codechallengeyape.presentation.home.HomeFragment
import com.example.codechallengeyape.presentation.map.LocationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    enum class Screens {
        Home,
        Details,
        Location
    }

    override fun createBinding(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun setupUiBehavior() {
        showFragment(Screens.Home)
    }

    override fun subscribeToEvents() {
        viewModel.currentScreen.observe(this) { screen ->
            showFragment(screen)
        }
    }

    private fun showFragment(screens: Screens) {
        supportFragmentManager.commit {

            setReorderingAllowed(true)
            val id = binding.mainContainer.id

            when(screens) {
                Screens.Home -> {
                    // we can alternatively send sata with a bundle
                    replace<HomeFragment>(id, args = bundleOf())
                    return@commit
                }
                Screens.Details -> {
                    replace<DetailsFragment>(id)
                }
                Screens.Location -> {
                    replace<LocationFragment>(id)
                }
            }

            addToBackStack(null)
        }
    }
}
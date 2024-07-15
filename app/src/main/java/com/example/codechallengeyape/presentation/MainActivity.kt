package com.example.codechallengeyape.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.codechallengeyape.R
import com.example.codechallengeyape.databinding.ActivityMainBinding
import com.example.codechallengeyape.framework.viewModels.MainViewModel
import com.example.codechallengeyape.presentation.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    enum class Screens {
        Home
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            showFragment(Screens.Home)
        }
    }

    private fun showFragment(screens: Screens) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)

            when(screens) {
                Screens.Home -> {
                    replace<HomeFragment>(binding.mainContainer.id, args = bundleOf())
                    return@commit
                }
            }

            addToBackStack(null)
        }
    }
}
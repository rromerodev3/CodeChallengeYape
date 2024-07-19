package com.example.codechallengeyape.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = createBinding(container)
        setupUiBehavior()
        (requireActivity() as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.title = getString(getScreenTitle())

        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToEvents()
        configureMenu()
    }

    /**
     * Use this function to create the root view
     */
    abstract fun createBinding(container: ViewGroup?): View
    /**
     * Use this function to set click listeners and other ui elements events
     */
    abstract fun setupUiBehavior()
    /**
     * Use this function to observe viewmodel events
     */
    abstract fun subscribeToEvents()
    /**
     * Use this function to set parent screen title
     */
    abstract fun getScreenTitle(): Int
    /**
     * Use this function to configure fragment menu
     */
    abstract fun configureMenu()
}
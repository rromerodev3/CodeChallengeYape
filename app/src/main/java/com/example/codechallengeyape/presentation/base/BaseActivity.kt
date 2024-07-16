package com.example.codechallengeyape.presentation.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = createBinding()
        setContentView(rootView)
        setupUiBehavior()
        subscribeToEvents()
    }

    /**
     * Use this function to create the root view
     */
    abstract fun createBinding(): View
    /**
     * Use this function to set click listeners and other ui elements events
     */
    abstract fun setupUiBehavior()
    /**
     * Use this function to observe viewmodel events
     */
    abstract fun subscribeToEvents()
}
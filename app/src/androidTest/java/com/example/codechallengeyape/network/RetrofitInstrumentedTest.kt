package com.example.codechallengeyape.network

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import javax.inject.Inject

@HiltAndroidTest
class RetrofitInstrumentedTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var retrofit: Retrofit

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testEndpointUrlIsCorrect() {
        val currentBaseUrl = retrofit.baseUrl().toUrl().toString()

        assertEquals("https://2639df36-fd4a-436a-943e-13a3bbc6b788.mock.pstmn.io/", currentBaseUrl)
    }
}
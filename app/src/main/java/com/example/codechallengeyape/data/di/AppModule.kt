package com.example.codechallengeyape.data.di

import android.content.Context
import com.example.codechallengeyape.data.network.api.RecipeService
import com.example.codechallengeyape.data.constants.Constants
import com.example.codechallengeyape.data.network.remote.RecipeRemoteDataSource
import com.example.codechallengeyape.data.repositories.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext appContext: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRecipeService(retrofit: Retrofit): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }

    @Provides
    fun provideRecipeRemoteDataSource(recipeService: RecipeService): RecipeRemoteDataSource {
        return RecipeRemoteDataSource(recipeService)
    }

    @Provides
    fun provideRecipeRepository(remoteDataSource: RecipeRemoteDataSource): RecipeRepository {
        return RecipeRepository(remoteDataSource)
    }
}
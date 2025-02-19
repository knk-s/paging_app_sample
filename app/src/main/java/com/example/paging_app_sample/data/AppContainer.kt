package com.example.paging_app_sample.data

import com.example.paging_app_sample.network.CategoryRankApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val recipesRepository: RecipesRepository
    val recipesPagingSource: RecipesPagingSource
    val recipesPagingDataRepository: RecipesPagingDataRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://app.rakuten.co.jp/services/api/Recipe/CategoryRanking/"

    private val retrofit =
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .build()

    private val categoryRankApiService: CategoryRankApiService by lazy {
        retrofit.create(CategoryRankApiService::class.java)
    }

    override val recipesRepository: RecipesRepository by lazy {
        NetworkRecipesRepository(categoryRankApiService)
    }

    override val recipesPagingSource: RecipesPagingSource by lazy {
        RecipesPagingSource(recipesRepository)
    }

    override val recipesPagingDataRepository: RecipesPagingDataRepository by lazy {
        RecipesPagingDataRepository(recipesPagingSource)
    }
}

package com.example.paging_app_sample.data

import com.example.paging_app_sample.BuildConfig
import com.example.paging_app_sample.model.Recipe
import com.example.paging_app_sample.network.CategoryRankApiService

interface RecipesRepository {
    suspend fun getRecipes(categoryId: String): List<Recipe>
}

class NetworkRecipesRepository(
    private val apiService: CategoryRankApiService,
) : RecipesRepository {
    override suspend fun getRecipes(categoryId: String): List<Recipe> {
        val applicationId = BuildConfig.apiKey
        val response =
            apiService.getRecipes(
                applicationId = applicationId,
                categoryId = categoryId,
            )
        return response.result.map { it.convertToRecipe() }
    }
}

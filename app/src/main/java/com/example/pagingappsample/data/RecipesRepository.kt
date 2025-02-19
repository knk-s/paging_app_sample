package com.example.pagingappsample.data

import com.example.pagingappsample.BuildConfig
import com.example.pagingappsample.model.Recipe
import com.example.pagingappsample.network.CategoryRankApiService

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

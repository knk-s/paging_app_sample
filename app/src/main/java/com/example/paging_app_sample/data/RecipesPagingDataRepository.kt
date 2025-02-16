package com.example.paging_app_sample.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging_app_sample.model.Recipe
import kotlinx.coroutines.flow.Flow

class RecipesPagingDataRepository(
    private val recipesPagingSource: RecipesPagingSource
) {
    fun getRecipes(): Flow<PagingData<Recipe>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            )
        ) {
            recipesPagingSource
        }.flow
    }

    companion object {
        // APIの仕様により4件ずつしかデータを取得できない
        private const val PAGE_SIZE = 4
    }
}
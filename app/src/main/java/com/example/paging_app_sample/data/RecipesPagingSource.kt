package com.example.paging_app_sample.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging_app_sample.model.Recipe

class RecipesPagingSource(
    private val recipesRepository: RecipesRepository
): PagingSource<Int, Recipe>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        try {
            val currentPage = params.key ?: DEFAULT_KEY

            val categoryId = getCategoryId(currentPage)
            val recipes = recipesRepository.getRecipes(categoryId)

            return LoadResult.Page(
                data = recipes,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (currentPage >= RiceDishMediumCategory.getMaxOrdinal()) null else currentPage + 1
            )
        } catch (e: Exception) {
            Log.d("RecipesPagingSource", "ERROR load() : " + e.message)
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    // "(大カテゴリID)-(中カテゴリID)"の形式のカテゴリIDを作成
    private fun getCategoryId(currentPage: Int): String {
        val mediumCategory = RiceDishMediumCategory.fromOrdinal(currentPage)
        return LargeCategory.RICE_DISHES.id + "-" + mediumCategory.id
    }

    companion object {
        // enumのordinalは0から始まるため0を指定
        private const val DEFAULT_KEY = 0
    }
}
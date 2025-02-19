package com.example.pagingappsample.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingappsample.model.Recipe
import kotlinx.coroutines.delay

class RecipesPagingSource(
    private val recipesRepository: RecipesRepository,
) : PagingSource<Int, Recipe>() {
    private var lastRequestTime: Long = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        try {
            delayBetweenRequests()

            val currentPage = params.key ?: DEFAULT_KEY

            val categoryId = getCategoryId(currentPage)
            val recipes = recipesRepository.getRecipes(categoryId)

            lastRequestTime = System.currentTimeMillis()

            return LoadResult.Page(
                data = recipes,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (currentPage >= RiceDishMediumCategory.getMaxOrdinal()) null else currentPage + 1,
            )
        } catch (e: Exception) {
            Log.d("RecipesPagingSource", "ERROR load() : " + e.message)
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    // APIの仕様により、リクエストを1秒に1回以下に制限
    private suspend fun delayBetweenRequests() {
        val currentTime = System.currentTimeMillis()
        val currentInterval = currentTime - lastRequestTime
        val minimumInterval = 1000

        if (lastRequestTime != 0L && currentInterval < minimumInterval) {
            delay(minimumInterval - currentInterval)
            Log.d("RecipesPagingSource", "Delay currentInterval : {$currentInterval}")
        }
    }

    // "(大カテゴリID)-(中カテゴリID)"形式のカテゴリIDを作成
    private fun getCategoryId(currentPage: Int): String {
        val mediumCategory = RiceDishMediumCategory.fromOrdinal(currentPage)
        return LargeCategory.RICE_DISHES.id + "-" + mediumCategory.id
    }

    companion object {
        // enumのordinalは0から始まるため
        private const val DEFAULT_KEY = 0
    }
}

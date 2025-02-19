package com.example.paging_app_sample.network

import com.example.paging_app_sample.data.CategoryRankResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryRankApiService {
    @GET("20170426")
    suspend fun getRecipes(
        @Query("applicationId") applicationId: String,
        @Query("categoryId") categoryId: String,
    ): CategoryRankResponse
}

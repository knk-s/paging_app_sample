package com.example.paging_app_sample.network

import com.example.paging_app_sample.data.CategoryRankResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val baseUrl = "https://app.rakuten.co.jp/services/api/Recipe/CategoryRanking/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(baseUrl)
    .build()

interface CategoryRankApiService {
    @GET("20170426")
    suspend fun getRecipes(
        @Query("applicationId") applicationId: String,
        @Query("categoryId") categoryId: String
    ): CategoryRankResponse
}

object CategoryRankApi {
    val api: CategoryRankApiService by lazy {
        retrofit.create(CategoryRankApiService::class.java)
    }
}


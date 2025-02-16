package com.example.paging_app_sample.data

import com.example.paging_app_sample.model.Recipe
import kotlinx.serialization.Serializable

// APIの出力パラメータ

@Serializable
data class CategoryRankResponse(
    val result: List<CategoryRankRecipe>
)

@Serializable
data class CategoryRankRecipe(
    val recipeId: Int,
    val recipeTitle: String,
    val recipeUrl: String,
    val foodImageUrl: String,
    val mediumImageUrl: String,
    val smallImageUrl: String,
    val pickup: Int,
    val shop: Int,
    val nickname: String,
    val recipeDescription: String,
    val recipeMaterial: List<String>,
    val recipeIndication: String,
    val recipeCost: String,
    val recipePublishday: String,
    val rank: Int
) {
    fun convertToRecipe(): Recipe {
        return Recipe(
            id = recipeId.toString(),
            title = recipeTitle,
            url = recipeUrl,
            imageUrl = foodImageUrl,
            description = recipeDescription
        )
    }
}
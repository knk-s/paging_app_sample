package com.example.paging_app_sample.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paging_app_sample.data.NetworkRecipesRepository
import com.example.paging_app_sample.model.Recipe
import kotlinx.coroutines.launch

sealed interface UiState {
    data class Success(val recipes: List<Recipe>) : UiState
    object Error : UiState
    object Loading : UiState
}

class RecipesViewModel : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            uiState = try {
                val recipesRepository = NetworkRecipesRepository()
                val result = recipesRepository.getRecipes()
                UiState.Success(result)
            } catch (e: Exception) {
                UiState.Error
            }
        }
    }


}
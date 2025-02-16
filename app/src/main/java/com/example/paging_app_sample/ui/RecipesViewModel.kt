package com.example.paging_app_sample.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.paging_app_sample.RecipesApplication
import com.example.paging_app_sample.data.RecipesRepository
import com.example.paging_app_sample.model.Recipe
import kotlinx.coroutines.launch

sealed interface UiState {
    data class Success(val recipes: List<Recipe>) : UiState
    object Error : UiState
    object Loading : UiState
}

class RecipesViewModel(private val recipesRepository: RecipesRepository) : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            uiState = try {
                val result = recipesRepository.getRecipes()
                UiState.Success(result)
            } catch (e: Exception) {
                Log.d("RecipesViewModel", "ERROR getRecipes() :" + e.message)
                UiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RecipesApplication)
                val repository = application.container.recipesRepository
                RecipesViewModel(repository)
            }
        }
    }
}
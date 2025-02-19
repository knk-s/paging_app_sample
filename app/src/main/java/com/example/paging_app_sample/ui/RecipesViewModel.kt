package com.example.paging_app_sample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging_app_sample.RecipesApplication
import com.example.paging_app_sample.data.RecipesPagingDataRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class RecipesViewModel(repository: RecipesPagingDataRepository) : ViewModel() {
    val recipes =
        repository.getRecipes()
            .cachedIn(viewModelScope)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = PagingData.empty(),
            )

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application = (this[APPLICATION_KEY] as RecipesApplication)
                    val repository = application.container.recipesPagingDataRepository
                    RecipesViewModel(repository)
                }
            }
    }
}

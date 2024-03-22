package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _categorieState = mutableStateOf(RecipeState())
    // Create a public variable that will expose the categorieState to external classes
    val categoriesState : State<RecipeState> = _categorieState

    init {
        // Get the categories the moment MainViewModel is initialized or called
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categorieState.value = _categorieState.value.copy(
                    list = response.categories,
                    loading = true,
                    error = null
                )

            } catch (e: Exception){
                // In case there is an error, send a categoryState with an error message
                _categorieState.value = _categorieState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}
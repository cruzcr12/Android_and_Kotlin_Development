package com.example.myrecipeapp

/**
 * Category for every meal
 */
data class Category(val idCategory:String,
                    val strCategory: String,
                    val strCategoryThumb: String,
                    val strCategoryDescription: String)

/**
 * Response coming from API with the list of categories
 */
data class CategoriesResponse(val categories: List<Category>)

package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Category for every meal
 */
@Parcelize
data class Category(val idCategory:String,
                    val strCategory: String,
                    val strCategoryThumb: String,
                    val strCategoryDescription: String): Parcelable

/**
 * Response coming from API with the list of categories
 */
data class CategoriesResponse(val categories: List<Category>)

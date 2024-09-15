package com.example.myrecipeapp

// Class to keep the screens for the app along with the routes
sealed class Screen(val route:String) {
    object RecipeScreen: Screen("recipeScreen")
    object DetailScreen: Screen("detailScreen")
}
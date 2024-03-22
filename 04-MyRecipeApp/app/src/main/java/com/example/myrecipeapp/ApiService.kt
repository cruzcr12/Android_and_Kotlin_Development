package com.example.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

// Create a retrofit instance with the configurations given
// Initialize the builder to configure and create retrofit instance
private val retrofit = Retrofit.Builder()
    // Define the base URL for the API endpoints
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    // Adds a converter factory to convert the JSON response into Kotlin objects
    .addConverterFactory(GsonConverterFactory.create())
    // Creates and returns a Retrofit instance configured with the settings specified before
    .build()

// Creates the implementation of the API endpoints, giving access to the API methods
val recipeService = retrofit.create(ApiService::class.java)

// The ApiService defines the endpoints we will access from the application
interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

}
package com.echcoding.mywishlistapp

import android.content.Context
import androidx.room.Room
import com.echcoding.mywishlistapp.data.WishDatabase
import com.echcoding.mywishlistapp.data.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy{
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(
            context,
            WishDatabase::class.java,
            "wishlist.db"
        ).build()
    }
}
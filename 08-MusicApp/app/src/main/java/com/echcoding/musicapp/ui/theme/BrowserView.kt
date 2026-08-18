package com.echcoding.musicapp.ui.theme

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.echcoding.musicapp.R

@Composable
fun BrowserView(){

    val categories = listOf("Hits", "Workout", "Metal", "Relax", "Party", "Post-rock")
    LazyVerticalGrid(columns = GridCells.Fixed(2))
    {
        items(categories) { cat ->
            BrowserItem(cat, R.drawable.ic_search)
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun BrowserPreview() {
    BrowserView()
}


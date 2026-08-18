package com.echcoding.musicapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.echcoding.musicapp.MockData
import com.echcoding.musicapp.R
import com.echcoding.musicapp.libraries

@Composable
fun LibraryView(){
    LazyColumn() {
        items(libraries) { lib ->
            LibraryItem(lib)
        }
    }
}


@Composable
fun LibraryItem(library: MockData){
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Row(){
                Icon(painter = painterResource(id = library.image), contentDescription = library.name)
                Text(text = library.name)
            }
            Icon(painter = painterResource(id = R.drawable.ic_arrow_forward), contentDescription = "See All")
        }
        HorizontalDivider(color = Color.LightGray)
    }

}
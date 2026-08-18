package com.echcoding.musicapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import com.echcoding.musicapp.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AccountView(){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                Icon(painterResource(id = R.drawable.ic_account_circle),
                    contentDescription = "Menu",
                    modifier = Modifier.padding(end = 8.dp))

                Column(){
                    Text("Sancho Panza")
                    Text("sanchopanza@example.com")
                }
            }
            IconButton(onClick = {}) {
                Icon(painterResource(id = R.drawable.ic_arrow_forward),contentDescription = null)
            }
        }

        Row(modifier = Modifier.padding(top = 16.dp)){
            Icon(
                painter = painterResource(id = R.drawable.ic_music_note),
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text="Ny Music")
        }
        HorizontalDivider()
    }

}
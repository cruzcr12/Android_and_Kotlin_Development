package com.echcoding.musicapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.echcoding.musicapp.R
import com.echcoding.musicapp.Screen

@Composable
fun Subscription(){
    Column(
        //modifier = Modifier.  .size(minHeight = 200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Manage Subscription")
        Card(
            modifier = Modifier.padding(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ){
            Column(modifier = Modifier.padding(8.dp)) {
                Column(){
                    Text("Musical")
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                        Text("Free Tier")
                        TextButton(onClick = { /* TODO */ }) {
                            Row{
                                Text("See All Plans")
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                                    contentDescription = "See All Plans"
                                )
                            }
                        }
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Row(Modifier.padding(vertical = 16.dp)){
                        Icon(painterResource(R.drawable.ic_account_box), contentDescription = "Get a Plan")
                        Text("Get a Plan")
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubscriptionPreview(){
    Subscription()
}
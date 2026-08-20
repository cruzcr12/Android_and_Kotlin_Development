package com.echcoding.myfirebasechatbot.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.echcoding.myfirebasechatbot.R
import com.echcoding.myfirebasechatbot.viewmodel.MessageViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen(roomId: String,
               messageViewModel: MessageViewModel = viewModel()
){
    val messages by messageViewModel.messages.observeAsState(emptyList())
    messageViewModel.setRoomId(roomId)
    val text = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        // Display chat messages
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(messages){message ->
                ChatMessageItem(message = message
                    .copy(isSentByCurrentUser = message.senderId == messageViewModel.currentUser.value?.email))
            }
        }

        // Chat input field and send icon
        Row(
           modifier = Modifier.fillMaxWidth()
               .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            BasicTextField(
                value = text.value,
                onValueChange = { text.value = it },
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                modifier = Modifier.weight(1f).padding(8.dp)
            )
            IconButton(
                onClick = {
                    // Send the message when the icon is clicked
                    if(text.value.isNotEmpty()){
                        messageViewModel.sendMessage(text.value.trim())
                        text.value = ""
                    }
                    messageViewModel.loadMessages()
                }
            ) {
                Icon(painterResource(R.drawable.ic_send), contentDescription = "Send")
            }
        }

    }

}
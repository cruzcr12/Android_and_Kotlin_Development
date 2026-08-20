package com.echcoding.myfirebasechatbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.echcoding.myfirebasechatbot.screen.ChatRoomListScreen
import com.echcoding.myfirebasechatbot.screen.ChatScreen
import com.echcoding.myfirebasechatbot.screen.LoginScreen
import com.echcoding.myfirebasechatbot.screen.Screen
import com.echcoding.myfirebasechatbot.screen.SignUpScreen
import com.echcoding.myfirebasechatbot.ui.theme.MyFirebaseChatbotTheme
import com.echcoding.myfirebasechatbot.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val authViewModel: AuthViewModel = viewModel()
            val navController = rememberNavController()
            MyFirebaseChatbotTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph(authViewModel = authViewModel, navController = navController)
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(
    authViewModel: AuthViewModel,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.SignUpScreen.route
    ){
        composable(Screen.SignUpScreen.route){
            SignUpScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = {
                    navController.navigate(Screen.LoginScreen.route)
                }
            )
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUpScreen.route)
                }
            ){
                navController.navigate(Screen.ChatRoomScreen.route)
            }
        }

        composable(Screen.ChatRoomScreen.route){
            ChatRoomListScreen{
                navController.navigate( "${Screen.ChatScreen.route}/${it.id}")
            }
        }

        composable("${Screen.ChatScreen.route}/{roomId}"){
            val roomId = it.arguments?.getString("roomId") ?: ""
            ChatScreen(roomId = roomId)
        }


    }

}
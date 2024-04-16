package com.example.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp(){
    // Create a navController
    val navController = rememberNavController()
    // Create a NavHost with a graph with all the destinations
    NavHost(navController = navController, startDestination = "firstScreen"){
        composable(route = "firstScreen"){
            FirstScreen { name ->
                // The route will have the name we pass as parameter
                navController.navigate("secondScreen/$name")
            }
        }
        // The second screen needs to accept a parameter for the name
        composable(route = "secondScreen/{name}"){
            // Get the value from the route
            val name = it.arguments?.getString("name") ?: "No Name"

            SecondScreen(name){
                navController.navigate("firstScreen")
            }
        }
        composable(route = "thirdScreen"){
            ThirdScreen {
                navController.navigate("firstScreen")
            }
        }
    }
}

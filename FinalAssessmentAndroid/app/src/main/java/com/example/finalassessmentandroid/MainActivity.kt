package com.example.finalassessmentandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalassessmentandroid.ui.theme.FinalAssessmentAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dishlistscreen") {
        composable(route = "dishlistscreen") {
            DishListScreen(navController)
        }
        composable(
            route="dishdetailscreen/{id}",
            arguments = listOf(navArgument(name = "id"){
                type = NavType.LongType
            })
        ) {
            val dishId = it.arguments?.getLong("id")
            dishId?.let {
                DishDetailScreen(dishId = dishId)
            }
        }
    }
}




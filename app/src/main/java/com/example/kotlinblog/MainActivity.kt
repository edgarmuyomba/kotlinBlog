package com.example.kotlinblog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinblog.ui.layout.homescreen.HomeScreen
import com.example.kotlinblog.ui.layout.search.SearchPage
import com.example.kotlinblog.ui.theme.KotlinBlogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinBlogTheme {
                KotlinBlogApp()
            }
        }
    }
}

@Composable
fun KotlinBlogApp(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(onSearchButtonClicked = { navController.navigate("search") })
        }
        composable(route = "search") {
            SearchPage(onBackButtonClicked = {
                navController.popBackStack(
                    "home",
                    inclusive = false
                )
            })
        }
    }
}
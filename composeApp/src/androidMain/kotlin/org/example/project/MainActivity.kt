package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.screens.ArmasScreen
import org.example.project.screens.GenericDetailScreen
import org.example.project.screens.HomeScreen
import org.example.project.screens.ItemsScreen
import org.example.project.screens.JefesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App1()
        }
    }
}

@Composable
fun App1() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController)}
        composable("armas") { ArmasScreen(navController)}
        composable("jefes") { JefesScreen(navController)}
        composable("Items") { ItemsScreen(navController)}
        composable("detail/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            GenericDetailScreen(navController = navController, itemName = name)
        }
    }

}


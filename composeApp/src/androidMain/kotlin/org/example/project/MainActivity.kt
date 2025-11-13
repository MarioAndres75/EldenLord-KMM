package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.screens.ArmasScreen
import org.example.project.screens.CenizasScreen
import org.example.project.screens.GenericDetailScreen
import org.example.project.screens.HomeScreen
import org.example.project.screens.ItemsScreen
import org.example.project.screens.JefesScreen
import org.example.project.screens.MagiasScreen
import org.example.project.screens.NpcScreen

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
        composable("items") { ItemsScreen(navController)}
        composable("npc") { NpcScreen(navController) }
        composable("cenizas") { CenizasScreen(navController) }
        composable("magias") { MagiasScreen(navController) }
        composable("detail/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            GenericDetailScreen(navController = navController, itemName = name)
        }
    }

}


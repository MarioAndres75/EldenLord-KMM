package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.example.project.R
import org.example.project.ui.eldenColor

data class HomeItem(
    val imageRes: Int,
    val name: String
)

val homeItems = listOf(
    HomeItem(R.drawable.armas, "Armas"),
    HomeItem(R.drawable.jefe, "Jefes"),
    HomeItem(R.drawable.items, "Items"),
    HomeItem(R.drawable.npc, "NPC"),
    HomeItem(R.drawable.cenizas, "Cenizas"),
    HomeItem(R.drawable.magias, "Magias")
)

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(eldenColor)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.elden_lord_logo),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxWidth().height(150.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(homeItems) { item ->
                MenuGridCard(item)
            }
        }
    }
}

@Composable
fun MenuGridCard(item: HomeItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* No hace nada por ahora */ }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen completa, que se ajuste al ancho del card
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )

            // Nombre debajo de la imagen
            Text(
                text = item.name,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp),
                color = Color.Black
            )
        }
    }
}

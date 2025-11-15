package org.example.project.screens

import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.clases.HomeImage
import org.example.project.data.getImagenesHome
import org.example.project.ui.fondoCard
import org.example.project.ui.mantequita

@Composable
fun MenuGrid(navController: NavHostController) {
    val data: List<HomeImage> = getImagenesHome()

    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(top = 10.dp)) {
        items(data) { card: HomeImage ->
            MenuGridCard(card) {
                if (card.name.equals("Armas", ignoreCase = true)) {
                    navController.navigate("armas")
                }  else if (card.name.equals("Jefes", ignoreCase = true)) {
                    navController.navigate("jefes")
                } else if (card.name.equals("Items", ignoreCase = true)) {
                    navController.navigate("items")
                }else if (card.name.equals("NPC", ignoreCase = true)) {
                    navController.navigate("npc")
                } else if (card.name.equals("Cenizas", ignoreCase = true)) {
                    navController.navigate("cenizas")
                }else if (card.name.equals("Magias", ignoreCase = true)) {
                    navController.navigate("magias")
                }
                else {
                    navController.navigate("detail/${card.name}")
                }
            }
        }
    }
}


@Composable
fun MenuGridCard(card: HomeImage, onBotonClick: () -> Unit) {
    val displayName = card.name.replaceFirstChar { it.uppercase() }

    OutlinedCard(
        modifier = Modifier
            .padding(10.dp)
            .clickable(onClick = onBotonClick)
    ) {
        Column(
            modifier = Modifier.background(color = mantequita),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .background(color = fondoCard)
                    .fillMaxWidth()
                    .size(100.dp),
                painter = painterResource(id = card.resourceId),
                contentDescription = null
            )
            Text(
                text = displayName,
                fontSize = 20.sp,
                modifier = Modifier
                    .background(color = mantequita)
                    .padding(4.dp)
            )
        }
    }
}


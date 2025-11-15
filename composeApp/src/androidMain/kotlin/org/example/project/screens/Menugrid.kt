package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import domain.model.HomeImage
import org.example.project.data.getImagenesHome

@Composable
fun MenuGrid(navController: NavHostController) {
    val data: List<HomeImage> = getImagenesHome()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement =
            androidx.compose.foundation.layout.Arrangement
                .spacedBy(16.dp),
        horizontalArrangement =
            androidx.compose.foundation.layout.Arrangement
                .spacedBy(16.dp),
    ) {
        items(data) { card: HomeImage ->
            MenuGridCard(card) {
                when (card.name.lowercase()) {
                    "armas" -> navController.navigate("armas")
                    "jefes" -> navController.navigate("jefes")
                    "items" -> navController.navigate("items")
                    "npc" -> navController.navigate("npc")
                    "cenizas" -> navController.navigate("cenizas")
                    "magias" -> navController.navigate("magias")
                    else -> navController.navigate("detail/${card.name}")
                }
            }
        }
    }
}

@Composable
fun MenuGridCard(
    card: HomeImage,
    onBotonClick: () -> Unit,
) {
    val displayName = card.name.replaceFirstChar { it.uppercase() }

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(180.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    clip = false,
                ).clickable(onClick = onBotonClick),
        shape = RoundedCornerShape(16.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = Color.Transparent,
            ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            // Imagen de fondo
            Image(
                painter = painterResource(id = card.resourceId),
                contentDescription = null,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
            )

            // Gradiente overlay para mejor legibilidad del texto
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(
                            brush =
                                Brush.verticalGradient(
                                    colors =
                                        listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.7f),
                                        ),
                                    startY = 0.5f,
                                ),
                        ).clip(RoundedCornerShape(16.dp)),
            )

            // Texto en la parte inferior
            Text(
                text = displayName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier =
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )

            // Efecto de borde sutil
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Transparent),
            )
        }
    }
}

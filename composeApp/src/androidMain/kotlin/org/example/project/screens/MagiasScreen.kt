package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import domain.model.Magia
import org.example.project.ui.fondoCard
import org.example.project.ui.mantequita
import presentation.viewModels.MagiasViewModel

@Composable
fun MagiasScreen(
    navController: NavHostController,
    viewModel: MagiasViewModel = MagiasViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // --- Barra superior ---
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(color = mantequita)
                    .padding(top = 24.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                // --- Botón Volver ---
                Box(
                    modifier =
                        Modifier
                            .clickable { navController.popBackStack() }
                            .padding(8.dp),
                ) {
                    Text(
                        text = "< Volver",
                        fontSize = 18.sp,
                        color = Color.Black,
                    )
                }

                // --- Título ---
                Text(
                    text = "Magias",
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier =
                        Modifier
                            .weight(1f)
                            .padding(end = 48.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }

        // --- Contenido ---
        when {
            state.isLoading ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }

            state.error != null ->
                Text(
                    text = "Error: ${state.error}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp),
                )

            else ->
                LazyColumn(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(color = fondoCard)
                            .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(state.magias) { magia ->
                        MagiaItem(magia)
                    }
                }
        }
    }
}

@Composable
fun MagiaItem(magia: Magia) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(magia.image ?: ""),
                contentDescription = magia.name,
                modifier = Modifier.size(128.dp), // mismo tamaño "grande" que NPC/Cenizas
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(magia.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(magia.description ?: "Sin descripción", fontSize = 14.sp)
            }
        }
    }
}

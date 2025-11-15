package org.example.project.screens

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import org.example.project.ui.mantequita
import presentation.viewModels.JefesViewModel

@Composable
fun JefesScreen(
    navController: NavHostController,
    viewModel: JefesViewModel = JefesViewModel(),
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

                // --- Título centrado ---
                Text(
                    text = "Jefes",
                    fontSize = 22.sp,
                    color = Color.Black,
                    modifier =
                        Modifier
                            .weight(1f)
                            .padding(end = 48.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }

        // --- Contenido principal ---
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                state.error != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Error: ${state.error}")
                    }
                }

                else -> {
                    LazyColumn(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                    ) {
                        items(state.jefes) { jefe ->
                            Card(modifier = Modifier.padding(8.dp)) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    jefe.image?.let {
                                        AsyncImage(
                                            model = it,
                                            contentDescription = jefe.name,
                                            modifier =
                                                Modifier
                                                    .fillMaxWidth()
                                                    .height(200.dp),
                                            contentScale = ContentScale.Crop,
                                        )
                                    }
                                    Text(
                                        jefe.name,
                                        style = MaterialTheme.typography.titleMedium,
                                    )
                                    jefe.description?.let {
                                        Text(it, style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.example.project.R
import org.example.project.ui.mantequita

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier =
            Modifier
                .background(
                    brush =
                        Brush.verticalGradient(
                            colors =
                                listOf(
                                    Color(0xFF1A1A1A),
                                    Color(0xFF2D1B0C),
                                    Color(0xFF1A1A1A),
                                ),
                        ),
                ).fillMaxSize()
                .padding(vertical = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Logo mejorado
        Image(
            painter = painterResource(id = R.drawable.elden_lord_logo),
            contentDescription = "Elden Ring Logo",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(horizontal = 32.dp),
            contentScale = ContentScale.Fit,
        )

        // Título de sección
        androidx.compose.material3.Text(
            text = "BASE DE DATOS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = mantequita,
            letterSpacing = 2.sp,
            modifier = Modifier.padding(bottom = 24.dp),
        )

        MenuGrid(navController)

        Spacer(modifier = Modifier.height(24.dp))

        // Texto decorativo
        androidx.compose.material3.Text(
            text = "Elige tu camino, Sin Luz",
            fontSize = 14.sp,
            color = Color.Gray,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

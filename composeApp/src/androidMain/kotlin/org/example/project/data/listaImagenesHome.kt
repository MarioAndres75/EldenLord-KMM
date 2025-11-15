package org.example.project.data

import androidx.compose.runtime.Composable
import domain.model.HomeImage
import org.example.project.R

@Composable
fun getImagenesHome(): List<HomeImage> =
    listOf(
        HomeImage(R.drawable.armas, "armas"),
        HomeImage(R.drawable.jefe, "jefes"),
        HomeImage(R.drawable.items, "items"),
        HomeImage(R.drawable.npc, "npc"),
        HomeImage(R.drawable.cenizas, "cenizas"),
        HomeImage(R.drawable.magias, "magias"),
    )

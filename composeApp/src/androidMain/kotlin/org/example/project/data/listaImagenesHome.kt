package org.example.project.data


import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import domain.clases.HomeImage
import org.example.project.R  // importá el R de tu módulo Android
import org.jetbrains.compose.resources.painterResource

@Composable
fun getImagenesHome(): List<HomeImage> {
    return listOf(
        HomeImage(R.drawable.armas, "armas"),
        HomeImage(R.drawable.jefe, "jefes"),
        HomeImage(R.drawable.items, "items"),
        HomeImage(R.drawable.npc, "npc"),
        HomeImage(R.drawable.cenizas, "cenizas"),
        HomeImage(R.drawable.magias, "magias")
    )
}


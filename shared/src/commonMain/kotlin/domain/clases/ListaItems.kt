package domain.clases

import kotlinx.serialization.Serializable

@Serializable
data class ListaItems(
    val data: List<Item>
)



package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ListaItems(
    val data: List<Item>,
)

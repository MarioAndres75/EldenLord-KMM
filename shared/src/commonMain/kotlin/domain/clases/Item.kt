package domain.clases

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: String,
    val name: String,
    val description: String? = null,
    val image: String? = null,
    val category: String? = null,
    val type: String? = null
)

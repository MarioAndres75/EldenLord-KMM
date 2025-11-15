package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Magia(
    val id: String,
    val name: String,
    val image: String? = null,
    val description: String? = null,
)

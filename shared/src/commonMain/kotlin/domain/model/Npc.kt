package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Npc(
    val id: String,
    val name: String,
    val image: String? = null,
    val quote: String? = null,
    val location: String? = null,
)

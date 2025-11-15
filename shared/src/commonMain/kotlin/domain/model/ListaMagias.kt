package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ListaMagias(
    val data: List<Magia>,
)

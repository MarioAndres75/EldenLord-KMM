package domain.clases


import kotlinx.serialization.Serializable

@Serializable
data class ListaMagias(
    val data: List<Magia>
)

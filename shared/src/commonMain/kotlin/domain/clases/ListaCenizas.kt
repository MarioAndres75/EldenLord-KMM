package domain.clases

import kotlinx.serialization.Serializable

@Serializable
data class ListaCenizas(
    val data: List<Ceniza>
)

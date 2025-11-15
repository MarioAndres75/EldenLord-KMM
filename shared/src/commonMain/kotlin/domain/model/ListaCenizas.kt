package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ListaCenizas(
    val data: List<Ceniza>,
)

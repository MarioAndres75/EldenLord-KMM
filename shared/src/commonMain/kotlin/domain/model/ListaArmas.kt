package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ListaArmas(
    val data: List<Arma>,
)

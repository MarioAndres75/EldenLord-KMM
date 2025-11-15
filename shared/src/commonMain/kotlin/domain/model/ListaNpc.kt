package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ListaNpc(
    val data: List<Npc>,
)

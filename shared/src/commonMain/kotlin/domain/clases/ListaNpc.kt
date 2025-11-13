package domain.clases

import kotlinx.serialization.Serializable

@Serializable
data class ListaNpc(
    val data: List<Npc>
)

package domain.clases

import kotlinx.serialization.Serializable

@Serializable
data class Ceniza(
    val id: String,
    val name: String,
    val image: String? = null,
    val description: String? = null,
    val effect: String? = null
)


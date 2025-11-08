package domain.clases



import kotlinx.serialization.Serializable

@Serializable
data class Arma(
    val id: String,
    val name: String,
    val image: String? = null,
    val description: String? = null,
    val attack: Int? = null
)
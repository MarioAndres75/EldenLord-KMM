package domain.clases



import kotlinx.serialization.Serializable

@Serializable
data class ListaArmas(
    val data: List<Arma>
)


package network




import domain.clases.Arma
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


class EldenRingAPI {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    private val BASE_URL = "https://eldenring.fanapis.com/api/weapons"

    suspend fun getWeapons(): List<Arma> {
        return try {
            val response: WeaponListResponse = client.get("$BASE_URL?limit=20").body()
            response.data
        } catch (e: Exception) {
            println("DEBUG: Error al obtener armas -> ${e.message}")
            emptyList()
        }
    }
}

@kotlinx.serialization.Serializable
data class WeaponListResponse(
    val data: List<Arma>
)

package network

import domain.clases.Arma
import domain.clases.ListaArmas
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class EldenRingAPI {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }

    private val BASE_URL = "https://eldenring.fanapis.com/api/weapons"

    suspend fun getWeapons(): List<Arma> {
        return try {
            val response: ListaArmas = client.get("$BASE_URL?limit=20").body()
            response.data
        } catch (e: Exception) {
            println("DEBUG: Error al obtener armas -> ${e.message}")
            emptyList()
        }
    }
}

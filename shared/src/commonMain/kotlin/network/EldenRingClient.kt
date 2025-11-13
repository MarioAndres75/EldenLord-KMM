package network

import domain.clases.Arma
import domain.clases.Ceniza
import domain.clases.Item
import domain.clases.Jefe
import domain.clases.ListaArmas
import domain.clases.ListaCenizas
import domain.clases.ListaItems
import domain.clases.ListaJefes
import domain.clases.ListaMagias
import domain.clases.ListaNpc
import domain.clases.Magia
import domain.clases.Npc
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Cliente de red para consultar la API pública de Elden Ring.
 * Compatible con Kotlin Multiplatform.
 */
object EldenRingClient {

    // Cliente HTTP Ktor (Multiplatform)
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true // Ignora campos no usados
                    isLenient = true // Permite JSON más flexible
                }
            )
        }
    }

    private const val BASE_URL = "https://eldenring.fanapis.com/api"

    // --- ARMAS ---
    suspend fun getWeapons(): List<Arma> {
        return try {
            val response: ListaArmas = client.get("$BASE_URL/weapons").body()
            response.data
        } catch (e: Exception) {
            println("DEBUG: Error al obtener armas -> ${e.message}")
            emptyList()
        }
    }

    // --- JEFES ---
    suspend fun getBosses(): List<Jefe> {
        return try {
            val response: ListaJefes = client.get("$BASE_URL/bosses").body()
            response.data
        } catch (e: Exception) {
            println("DEBUG: Error al obtener jefes -> ${e.message}")
            emptyList()
        }
    }

    // --- ITEMS ---
    suspend fun getItems(): List<Item> {
        return try {
            val response: ListaItems = client.get("$BASE_URL/items").body()
            response.data
        } catch (e: Exception) {
            println("DEBUG: Error al obtener items -> ${e.message}")
            emptyList()
        }
    }
    // --- NPC ---
    suspend fun getNpcs(): List<Npc> {
        return try {
            val response: ListaNpc = client.get("$BASE_URL/npcs").body()
            response.data
        } catch (e: Exception) {
            println("DEBUG: Error al obtener NPCs -> ${e.message}")
            emptyList()
        }
    }


   // --- CENIZAS ---
    suspend fun getCenizas(): List<Ceniza> {
        return try {
            val response: ListaCenizas = client.get("$BASE_URL/ashes").body()
            response.data
        } catch (e: Exception) {
            println("DEBUG: Error al obtener cenizas -> ${e.message}")
            emptyList()
        }
    }
    // --- MAGIAS ---
    suspend fun getMagias(): List<Magia> {
        return try {
            val response: ListaMagias = client.get("$BASE_URL/spells").body()
            response.data
        } catch (e: Exception) {
            println("DEBUG: Error al obtener magias -> ${e.message}")
            emptyList()
        }
    }

    // --- CONJUROS / INCANTATIONS ---
    suspend fun getIncantations(): List<Magia> {
        return try {
            val response: ListaMagias = client.get("$BASE_URL/incantations").body()
            response.data
        } catch (e: Exception) {
            println("DEBUG: Error al obtener conjuros -> ${e.message}")
            emptyList()
        }
    }

}

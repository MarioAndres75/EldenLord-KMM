package ViewModels

import domain.clases.Magia
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import network.EldenRingClient

class MagiasViewModel : KmmViewModel() {

    private val api = EldenRingClient

    private val _state = MutableStateFlow(MagiasState())
    val state: StateFlow<MagiasState> = _state

    init {
        getMagias()
    }

    fun getMagias() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            try {
                // Ejecutar ambas llamadas en paralelo para acelerar la carga
                val spellsDeferred = async { api.getMagias() }
                val incantsDeferred = async { api.getIncantations() }

                val spells = spellsDeferred.await()
                val incants = incantsDeferred.await()

                val combined = (spells + incants).sortedBy { it.name.lowercase() } // opcional: ordenar por nombre

                _state.value = _state.value.copy(
                    isLoading = false,
                    magias = combined,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}

data class MagiasState(
    val isLoading: Boolean = false,
    val magias: List<Magia> = emptyList(),
    val error: String? = null
)


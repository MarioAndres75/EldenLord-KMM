package ViewModels

import domain.clases.Npc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.EldenRingClient

class NpcViewModel : KmmViewModel() {

    private val api = EldenRingClient

    private val _state = MutableStateFlow(NpcState())
    val state: StateFlow<NpcState> = _state

    init {
        getNpcs()
    }

    fun getNpcs() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            try {
                val npcs = api.getNpcs()
                _state.value = _state.value.copy(
                    isLoading = false,
                    npcs = npcs,
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

data class NpcState(
    val isLoading: Boolean = false,
    val npcs: List<Npc> = emptyList(),
    val error: String? = null
)

package ViewModels

import domain.clases.Arma
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.EldenRingAPI

class ArmasViewModel : KmmViewModel() {

    private val api = EldenRingAPI()

    private val _state = MutableStateFlow(ArmasState())
    val state: StateFlow<ArmasState> = _state

    init {
        getArmas()
    }

    fun getArmas() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            try {
                val armas = api.getWeapons()
                _state.value = _state.value.copy(
                    isLoading = false,
                    armas = armas,
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

data class ArmasState(
    val isLoading: Boolean = false,
    val armas: List<Arma> = emptyList(),
    val error: String? = null
)

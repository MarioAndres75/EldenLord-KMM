package ViewModels



import domain.clases.Arma
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.EldenRingAPI

class ArmasViewModel {
    private val api = EldenRingAPI()
    private val viewModelScope = CoroutineScope(Dispatchers.Default)

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
                _state.value = _state.value.copy(isLoading = false, armas = armas)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}

data class ArmasState(
    val isLoading: Boolean = false,
    val armas: List<Arma> = emptyList(),
    val error: String? = null
)

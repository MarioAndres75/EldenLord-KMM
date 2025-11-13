package ViewModels



import domain.clases.Jefe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.EldenRingClient

class JefesViewModel : KmmViewModel() {

    private val api = EldenRingClient

    private val _state = MutableStateFlow(JefesState())
    val state: StateFlow<JefesState> = _state

    init {
        getJefes()
    }

    fun getJefes() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val jefes = api.getBosses()
                _state.value = _state.value.copy(isLoading = false, jefes = jefes)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}

data class JefesState(
    val isLoading: Boolean = false,
    val jefes: List<Jefe> = emptyList(),
    val error: String? = null
)

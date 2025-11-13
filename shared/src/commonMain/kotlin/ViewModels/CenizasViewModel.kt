package ViewModels


import domain.clases.Ceniza
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.EldenRingClient

class CenizasViewModel : KmmViewModel() {

    private val api = EldenRingClient

    private val _state = MutableStateFlow(CenizasState())
    val state: StateFlow<CenizasState> = _state

    init {
        getCenizas()
    }

    fun getCenizas() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val cenizas = api.getCenizas()
                _state.value = _state.value.copy(
                    isLoading = false,
                    cenizas = cenizas
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}

data class CenizasState(
    val isLoading: Boolean = false,
    val cenizas: List<Ceniza> = emptyList(),
    val error: String? = null
)

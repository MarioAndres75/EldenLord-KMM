package ViewModels

import domain.clases.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.EldenRingClient

class ItemsViewModel : KmmViewModel() {

    private val api = EldenRingClient

    private val _state = MutableStateFlow(ItemsState())
    val state: StateFlow<ItemsState> = _state

    init {
        getItems()
    }

    fun getItems() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            try {
                val items = api.getItems()
                _state.value = _state.value.copy(
                    isLoading = false,
                    items = items,
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

data class ItemsState(
    val isLoading: Boolean = false,
    val items: List<Item> = emptyList(),
    val error: String? = null
)

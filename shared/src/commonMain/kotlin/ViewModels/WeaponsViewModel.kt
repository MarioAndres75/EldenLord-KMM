package ViewModels

import domain.clases.Arma
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import network.EldenRingAPI

class WeaponsViewModel(
    private val api: EldenRingAPI = EldenRingAPI()
) {
    private val _weapons = MutableStateFlow<List<Arma>>(emptyList())
    val weapons: StateFlow<List<Arma>> = _weapons

    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    fun loadWeapons() {
        viewModelScope.launch {
            val result = api.getWeapons()
            _weapons.value = result
        }
    }
}

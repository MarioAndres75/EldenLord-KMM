package ViewModels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class KmmViewModel {
    protected val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    // Llamar desde Android/iOS cuando la pantalla/vista se destruye
    open fun onCleared() {
        viewModelScope.cancel()
    }
}

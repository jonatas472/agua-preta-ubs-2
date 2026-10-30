package br.edu.ifpe.ubsaude

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

/**
 * Fronteira de Arquivo: Disponibiliza os serviços locais para a tela Compose.
 */
class ServicoViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).servicoDao()

    private val _erroAoCarregar = MutableStateFlow(false)
    val erroAoCarregar: StateFlow<Boolean> = _erroAoCarregar.asStateFlow()

    private val _carregando = MutableStateFlow(true)
    val carregando: StateFlow<Boolean> = _carregando.asStateFlow()

    val servicos: StateFlow<List<Servico>> = dao.listar()
        .onEach { _carregando.value = false }
        .catch { exception ->
            Log.e(TAG, "Não foi possível carregar os serviços locais.", exception)
            _erroAoCarregar.value = true
            _carregando.value = false
            emit(emptyList())
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private companion object {
        const val TAG = "ServicoViewModel"
    }
}

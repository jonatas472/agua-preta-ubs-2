package br.edu.ifpe.ubsaude

import android.app.Application
import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class ResultadoAgendamento {
    SALVO,
    CAMPOS_OBRIGATORIOS,
    ERRO_AO_SALVAR
}

/**
 * Fronteira de Arquivo: Conecta as telas de Agendamento às operações do Room.
 */
class AgendamentoViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).agendamentoDao()

    private val _erroAoCarregar = MutableStateFlow(false)
    val erroAoCarregar: StateFlow<Boolean> = _erroAoCarregar.asStateFlow()

    private val _carregando = MutableStateFlow(true)
    val carregando: StateFlow<Boolean> = _carregando.asStateFlow()

    val agendamentos: StateFlow<List<Agendamento>> = dao.listar()
        .onEach { _carregando.value = false }
        .catch { exception ->
            Log.e(TAG, "Não foi possível carregar os agendamentos locais.", exception)
            _erroAoCarregar.value = true
            _carregando.value = false
            emit(emptyList())
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val _ultimoAgendamento = MutableStateFlow<Agendamento?>(null)
    val ultimoAgendamento: StateFlow<Agendamento?> = _ultimoAgendamento.asStateFlow()

    private val _resultadoSalvamento = MutableSharedFlow<ResultadoAgendamento>()
    val resultadoSalvamento = _resultadoSalvamento.asSharedFlow()

    private val _salvando = MutableStateFlow(false)
    val salvando: StateFlow<Boolean> = _salvando.asStateFlow()

    // Valida os campos obrigatórios e salva o agendamento em uma coroutine.
    fun salvar(agendamento: Agendamento) {
        if (
            agendamento.nome.isBlank() ||
            agendamento.data.isBlank() ||
            agendamento.cartaoSUS.isBlank() ||
            agendamento.horario.isBlank() ||
            agendamento.servico.isBlank()
        ) {
            viewModelScope.launch {
                _resultadoSalvamento.emit(ResultadoAgendamento.CAMPOS_OBRIGATORIOS)
            }
            return
        }

        if (_salvando.value) return

        viewModelScope.launch {
            _salvando.value = true
            try {
                dao.inserir(agendamento)
                _ultimoAgendamento.value = agendamento
                _resultadoSalvamento.emit(ResultadoAgendamento.SALVO)
            } catch (exception: SQLiteException) {
                Log.e(TAG, "Não foi possível salvar o agendamento no banco local.", exception)
                _resultadoSalvamento.emit(ResultadoAgendamento.ERRO_AO_SALVAR)
            } finally {
                _salvando.value = false
            }
        }
    }

    private companion object {
        const val TAG = "AgendamentoViewModel"
    }
}

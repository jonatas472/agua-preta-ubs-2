package br.edu.ifpe.ubsaude

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class EstadoAgendamento(
    val agendamentoSalvo: Agendamento? = null,
    val erroAoSalvar: Boolean = false,
    val erroValidacao: AgendamentoValidation.Erro? = null,
    val salvando: Boolean = false
)

/** Coordena a leitura e a persistência de agendamentos no Room. */
class AgendamentoViewModel(private val repository: AgendamentoRepository) : ViewModel() {
    private val _uiState =
        MutableStateFlow<UiState<List<Agendamento>>>(UiState.Carregando)
    val uiState: StateFlow<UiState<List<Agendamento>>> = _uiState.asStateFlow()

    private var carregamentoJob: Job? = null

    val agendamentos: StateFlow<List<Agendamento>> = uiState
        .map { estado ->
            when (estado) {
                UiState.Carregando, UiState.Vazio, is UiState.Erro -> emptyList()
                is UiState.Sucesso -> estado.dados
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val erroAoCarregar: StateFlow<Boolean> = uiState
        .map { it is UiState.Erro }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val carregando: StateFlow<Boolean> = uiState
        .map { it == UiState.Carregando }
        .stateIn(viewModelScope, SharingStarted.Eagerly, true)

    private val _estado = MutableStateFlow(EstadoAgendamento())
    val estado = _estado.asStateFlow()

    init {
        carregarAgendamentos()
    }

    /** Observa novamente o Room e publica vazio, erro ou os dados carregados. */
    fun carregarAgendamentos() {
        carregamentoJob?.cancel()
        _uiState.value = UiState.Carregando
        carregamentoJob = viewModelScope.launch {
            try {
                repository.agendamentos.collect { agendamentos ->
                    _uiState.value = if (agendamentos.isEmpty()) {
                        UiState.Vazio
                    } else {
                        UiState.Sucesso(agendamentos)
                    }
                }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                Log.e(TAG, "Não foi possível carregar os agendamentos locais.", exception)
                _uiState.value = UiState.Erro(R.string.error_loading_appointments)
            }
        }
    }

    /** Limpa o resultado anterior para uma nova tentativa de agendamento. */
    fun limparResultado() {
        _estado.value = EstadoAgendamento()
    }

    /** Valida os dados e só publica sucesso após receber um ID válido do Room. */
    fun salvar(agendamento: Agendamento, servicosDisponiveis: Set<String>) {
        if (_estado.value.salvando) return

        val erro = AgendamentoValidation.validar(
            nome = agendamento.nome,
            cartaoSUS = agendamento.cartaoSUS,
            data = agendamento.data,
            horario = agendamento.horario,
            servico = agendamento.servico,
            servicosPermitidos = servicosDisponiveis
        )
        if (erro != null) {
            _estado.value = EstadoAgendamento(erroValidacao = erro)
            return
        }

        _estado.value = EstadoAgendamento(salvando = true)
        viewModelScope.launch {
            try {
                val id = repository.salvar(agendamento)
                _estado.value = if (id in 1..Int.MAX_VALUE.toLong()) {
                    EstadoAgendamento(agendamentoSalvo = agendamento.copy(id = id.toInt()))
                } else {
                    Log.e(TAG, "O Room retornou um identificador inválido para o agendamento: $id")
                    EstadoAgendamento(erroAoSalvar = true)
                }
            } catch (exception: Exception) {
                if (exception is CancellationException) throw exception
                Log.e(TAG, "Não foi possível salvar o agendamento local.", exception)
                _estado.value = EstadoAgendamento(erroAoSalvar = true)
            } finally {
                if (_estado.value.salvando) {
                    _estado.value = _estado.value.copy(salvando = false)
                }
            }
        }
    }

    private companion object {
        const val TAG = "AgendamentoViewModel"
    }
}

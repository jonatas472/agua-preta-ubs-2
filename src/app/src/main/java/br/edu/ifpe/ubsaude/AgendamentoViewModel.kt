package br.edu.ifpe.ubsaude

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
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
    private val _erroAoCarregar = MutableStateFlow(false)
    val erroAoCarregar: StateFlow<Boolean> = _erroAoCarregar.asStateFlow()

    private val _carregando = MutableStateFlow(true)
    val carregando: StateFlow<Boolean> = _carregando.asStateFlow()

    val agendamentos: StateFlow<List<Agendamento>> = repository.agendamentos
        .onEach {
            _erroAoCarregar.value = false
            _carregando.value = false
        }
        .catch { exception ->
            if (exception is CancellationException) throw exception
            Log.e(TAG, "Não foi possível carregar os agendamentos locais.", exception)
            _erroAoCarregar.value = true
            _carregando.value = false
            emit(emptyList())
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val _estado = MutableStateFlow(EstadoAgendamento())
    val estado = _estado.asStateFlow()

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

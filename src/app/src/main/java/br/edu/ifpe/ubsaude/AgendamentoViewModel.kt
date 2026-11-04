package br.edu.ifpe.ubsaude

import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class EstadoAgendamento(
    val agendamentoSalvo: Agendamento? = null,
    val erroAoSalvar: Boolean = false,
    val salvando: Boolean = false
)

/** Coordena a persistência e publica o resultado somente após confirmação do Room. */
class AgendamentoViewModel(private val repository: AgendamentoRepository) : ViewModel() {
    val agendamentos = repository.agendamentos

    private val _estado = MutableStateFlow(EstadoAgendamento())
    val estado = _estado.asStateFlow()

    /** Limpa o resultado anterior para uma nova tentativa de agendamento. */
    fun limparResultado() {
        _estado.value = EstadoAgendamento()
    }

    /** Salva em coroutine e só publica sucesso após receber um ID válido do Room. */
    fun salvar(agendamento: Agendamento) {
        _estado.value = EstadoAgendamento(salvando = true)
        viewModelScope.launch {
            try {
                val id = repository.salvar(agendamento)
                _estado.value = if (id in 1..Int.MAX_VALUE.toLong()) {
                    EstadoAgendamento(agendamentoSalvo = agendamento.copy(id = id.toInt()))
                } else {
                    EstadoAgendamento(erroAoSalvar = true)
                }
            } catch (exception: Exception) {
                if (exception is CancellationException) throw exception
                Log.e(TAG, "Não foi possível salvar o agendamento local.", exception)
                _estado.value = EstadoAgendamento(erroAoSalvar = true)
            }
        }
    }

    private companion object {
        const val TAG = "AgendamentoViewModel"
    }
}
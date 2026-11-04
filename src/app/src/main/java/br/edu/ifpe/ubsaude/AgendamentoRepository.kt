package br.edu.ifpe.ubsaude

import kotlinx.coroutines.flow.Flow

/** Encaminha gravações e consultas de agendamento para a fonte Room existente. */
class AgendamentoRepository(private val agendamentoDao: AgendamentoDao) {
    val agendamentos: Flow<List<Agendamento>> = agendamentoDao.listar()

    /** Persiste um registro e retorna o ID confirmado pelo banco local. */
    suspend fun salvar(agendamento: Agendamento): Long = agendamentoDao.inserir(agendamento)
}
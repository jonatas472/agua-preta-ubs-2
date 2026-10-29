package br.edu.ifpe.ubsaude

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Fronteira de Arquivo: Operações locais de persistência para Agendamento.
 */
@Dao
interface AgendamentoDao {
    // Insere o agendamento e retorna o identificador gerado pelo Room.
    @Insert
    suspend fun inserir(agendamento: Agendamento): Long

    // Observa os agendamentos mais recentes primeiro.
    @Query("SELECT * FROM Agendamento ORDER BY id DESC")
    fun listar(): Flow<List<Agendamento>>
}

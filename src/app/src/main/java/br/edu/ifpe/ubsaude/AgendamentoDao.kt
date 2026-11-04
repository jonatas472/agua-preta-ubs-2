package br.edu.ifpe.ubsaude

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/** Operações Room usadas para salvar e observar os agendamentos locais. */
@Dao
interface AgendamentoDao {
    /** Insere o agendamento e retorna o identificador gerado pelo Room. */
    @Insert
    suspend fun inserir(agendamento: Agendamento): Long

    /** Observa os registros mais recentes primeiro para atualizar as telas automaticamente. */
    @Query("SELECT * FROM Agendamento ORDER BY id DESC")
    fun listar(): Flow<List<Agendamento>>
}
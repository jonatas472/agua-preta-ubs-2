package br.edu.ifpe.ubsaude

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Fronteira de Arquivo: Operações locais de consulta para Serviço.
 */
@Dao
interface ServicoDao {
    // Observa os serviços cadastrados em ordem de inserção.
    @Query("SELECT * FROM Servico ORDER BY id ASC")
    fun listar(): Flow<List<Servico>>
}

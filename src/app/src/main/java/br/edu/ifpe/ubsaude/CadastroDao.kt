package br.edu.ifpe.ubsaude

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Fronteira de Arquivo: Operações locais de persistência para Cadastro.
 */
@Dao
interface CadastroDao {
    // Insere um novo cadastro e retorna o identificador gerado pelo Room.
    @Insert
    suspend fun inserir(cadastro: Cadastro): Long

    // Observa os cadastros armazenados, ordenados pelo nome.
    @Query("SELECT * FROM Cadastro ORDER BY nome ASC")
    fun listar(): Flow<List<Cadastro>>
}

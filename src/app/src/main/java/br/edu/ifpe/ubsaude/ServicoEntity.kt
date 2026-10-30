package br.edu.ifpe.ubsaude

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Fronteira de Arquivo: Definição da Entidade Serviço do UBS+.
 */
@Entity
data class Servico(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val descricao: String,
    val horario: String
)

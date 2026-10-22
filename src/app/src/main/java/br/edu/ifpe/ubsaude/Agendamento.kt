package br.edu.ifpe.ubsaude

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Fronteira de Arquivo: Definição da Entidade Agendamento do UBS+
 * Baseado no plano do Grupo Apollo.
 */
@Entity
data class Agendamento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val data: String,
    val cartaoSUS: String,
    val horario: String,
    val servico: String
)
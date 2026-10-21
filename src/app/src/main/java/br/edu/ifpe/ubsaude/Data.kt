package br.edu.ifpe.ubsaude

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Fronteira de Arquivo: Definição das Entidades (Dados) do UBS+
 * Baseado no plano do Grupo Apollo.
 * Estas classes definem como as informações serão salvas no futuro.
 */

@Entity
data class Cadastro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val cartaoSUS: String,
    val dataNascimento: String,
    val telefone: String
)

@Entity
data class Agendamento(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val data: String,
    val cartaoSUS: String,
    val horario: String,
    val servico: String
)

@Entity
data class Servico(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val descricao: String,
    val horario: String
)

@Entity
data class Vacina(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val vacina: String,
    val afirmacao: String,
    val resposta: String,
    val explicacao: String
)

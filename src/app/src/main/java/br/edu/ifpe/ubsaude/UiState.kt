package br.edu.ifpe.ubsaude

/** Fronteira de Arquivo: Representa os estados de carregamento exibidos pelas telas. */
sealed interface UiState<out T> {
    data object Carregando : UiState<Nothing>
    data object Vazio : UiState<Nothing>
    data class Erro(val mensagemRecurso: Int) : UiState<Nothing>
    data class Sucesso<T>(val dados: T) : UiState<T>
}

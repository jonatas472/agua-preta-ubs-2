package br.edu.ifpe.ubsaude

import java.text.SimpleDateFormat
import java.text.ParseException
import java.util.Locale

/** Reúne validações independentes da interface para impedir agendamentos incompletos. */
object AgendamentoValidation {
    private val servicosPermitidos = setOf("Clínico Geral", "Odontologia", "Vacinação", "Pré-Natal")

    enum class Erro(val recurso: Int) {
        NOME(R.string.error_name_required),
        CARTAO_SUS(R.string.error_sus_invalid),
        DATA(R.string.error_date_invalid),
        HORARIO(R.string.error_time_invalid),
        SERVICO(R.string.error_service_required)
    }

    /** Retorna a primeira mensagem que explica por que o formulário não pode ser salvo. */
    fun validar(nome: String, cartaoSUS: String, data: String, horario: String, servico: String): Erro? {
        return when {
            nome.isBlank() -> Erro.NOME
            !cartaoSUS.matches(Regex("\\d{15}")) -> Erro.CARTAO_SUS
            !dataValida(data) -> Erro.DATA
            !horario.matches(Regex("(?:[01]\\d|2[0-3]):[0-5]\\d")) -> Erro.HORARIO
            servico !in servicosPermitidos -> Erro.SERVICO
            else -> null
        }
    }

    /** Formata até oito dígitos em DD/MM/AAAA durante a digitação. */
    fun formatarData(valor: String): String {
        val digitos = valor.filter(Char::isDigit).take(8)
        return when {
            digitos.length <= 2 -> digitos
            digitos.length <= 4 -> "${digitos.take(2)}/${digitos.drop(2)}"
            else -> "${digitos.take(2)}/${digitos.substring(2, 4)}/${digitos.drop(4)}"
        }
    }

    /** Formata até quatro dígitos em HH:MM durante a digitação. */
    fun formatarHorario(valor: String): String {
        val digitos = valor.filter(Char::isDigit).take(4)
        return if (digitos.length <= 2) digitos else "${digitos.take(2)}:${digitos.drop(2)}"
    }

    /** Confere formato e existência real da data, sem aceitar dias fora do calendário. */
    private fun dataValida(data: String): Boolean {
        if (!data.matches(Regex("\\d{2}/\\d{2}/\\d{4}"))) return false
        return try {
            SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).apply { isLenient = false }.parse(data) != null
        } catch (_: ParseException) {
            false
        }
    }
}
package br.edu.ifpe.ubsaude

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AgendamentoValidationTest {
    private val servicosPermitidos = setOf("Clínico Geral", "Odontologia", "Vacinação", "Pré-Natal")

    @Test
    fun rejectsMissingAndMalformedValues() {
        assertEquals(AgendamentoValidation.Erro.NOME, AgendamentoValidation.validar("", "123456789012345", "04/11/2026", "09:30", "Odontologia", servicosPermitidos))
        assertEquals(AgendamentoValidation.Erro.CARTAO_SUS, AgendamentoValidation.validar("Ana", "abc", "04/11/2026", "09:30", "Odontologia", servicosPermitidos))
        assertEquals(AgendamentoValidation.Erro.DATA, AgendamentoValidation.validar("Ana", "123456789012345", "31/02/2026", "09:30", "Odontologia", servicosPermitidos))
        assertEquals(AgendamentoValidation.Erro.HORARIO, AgendamentoValidation.validar("Ana", "123456789012345", "04/11/2026", "25:30", "Odontologia", servicosPermitidos))
        assertEquals(AgendamentoValidation.Erro.SERVICO, AgendamentoValidation.validar("Ana", "123456789012345", "04/11/2026", "09:30", "Outro", servicosPermitidos))
    }

    @Test
    fun acceptsCompleteValidAppointment() {
        assertNull(AgendamentoValidation.validar("Ana", "123456789012345", "04/11/2026", "09:30", "Odontologia", servicosPermitidos))
    }

    @Test
    fun acceptsOnlyServicesProvidedByRoom() {
        assertNull(AgendamentoValidation.validar("Ana", "123456789012345", "04/11/2026", "09:30", "Serviço novo", setOf("Serviço novo")))
        assertEquals(
            AgendamentoValidation.Erro.SERVICO,
            AgendamentoValidation.validar("Ana", "123456789012345", "04/11/2026", "09:30", "Odontologia", emptySet())
        )
    }

    @Test
    fun formatsDigitsAsDateAndTime() {
        assertEquals("04/11/2026", AgendamentoValidation.formatarData("04112026"))
        assertEquals("09:30", AgendamentoValidation.formatarHorario("0930"))
    }
}
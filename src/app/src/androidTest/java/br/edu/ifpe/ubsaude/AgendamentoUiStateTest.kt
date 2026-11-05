package br.edu.ifpe.ubsaude

import android.database.sqlite.SQLiteException
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.ViewModelStore
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.edu.ifpe.ubsaude.ui.theme.UBSTheme
import java.util.concurrent.atomic.AtomicBoolean

/** Testes instrumentados dos estados de carregamento e recuperação da tela inicial. */
@RunWith(AndroidJUnit4::class)
class AgendamentoUiStateTest {
    @get:Rule
    val composeRule = createComposeRule()
    private val viewModelStore = ViewModelStore()

    @After
    fun limparViewModels() {
        viewModelStore.clear()
    }

    @Test
    fun emptyDatabaseShowsEmptyState() = executarNoWorker {
        val viewModel = criarViewModel { flow { emit(emptyList()) } }

        assertEquals(UiState.Vazio, aguardar(viewModel) { it == UiState.Vazio })
    }

    @Test
    fun databaseFailureShowsErrorAndKeepsViewModelAlive() = executarNoWorker {
        val viewModel = criarViewModel {
            flow { throw SQLiteException("database unavailable") }
        }

        val state = aguardar(viewModel) { it is UiState.Erro }
        assertEquals(R.string.error_loading_appointments, (state as UiState.Erro).mensagemRecurso)
        assertFalse(viewModel.carregando.value)
    }

    @Test
    fun retryAfterDatabaseFailureLoadsAppointments() = executarNoWorker {
        var fail = true
        val viewModel = criarViewModel {
            flow {
                if (fail) throw SQLiteException("database unavailable")
                emit(listOf(agendamentoExemplo))
            }
        }

        aguardar(viewModel) { it is UiState.Erro }
        fail = false
        viewModel.carregarAgendamentos()

        val state = aguardar(viewModel) { it is UiState.Sucesso }
        assertEquals(listOf(agendamentoExemplo), (state as UiState.Sucesso).dados)
    }

    @Test
    fun delayedDatabaseQueryStaysLoadingUntilDataArrives() = executarNoWorker {
        val resposta = CompletableDeferred<List<Agendamento>>()
        val viewModel = criarViewModel {
            flow { emit(resposta.await()) }
        }

        delay(100)
        assertEquals(UiState.Carregando, viewModel.uiState.value)
        resposta.complete(listOf(agendamentoExemplo))
        assertTrue(aguardar(viewModel) { it is UiState.Sucesso } is UiState.Sucesso)
    }

    @Test
    fun cancellingOldQueryDuringRetryDoesNotBecomeAnError() = executarNoWorker {
        var tentativa = 0
        val viewModel = criarViewModel {
            flow {
                if (++tentativa == 1) awaitCancellation()
                emit(listOf(agendamentoExemplo))
            }
        }

        delay(100)
        viewModel.carregarAgendamentos()
        val state = aguardar(viewModel) { it is UiState.Sucesso || it is UiState.Erro }

        assertTrue(state is UiState.Sucesso)
    }

    @Test
    fun loadingStateShowsProgressMessage() {
        mostrarEstado(UiState.Carregando)

        composeRule.onNodeWithText("Carregando agendamentos...").assertIsDisplayed()
    }

    @Test
    fun emptyStateShowsHelpfulMessage() {
        mostrarEstado(UiState.Vazio)

        composeRule.onNodeWithText("Nenhum agendamento encontrado.").assertIsDisplayed()
    }

    @Test
    fun errorStateShowsMessageAndRetryButton() {
        val tentouNovamente = AtomicBoolean(false)
        mostrarEstado(UiState.Erro(R.string.error_loading_appointments)) {
            tentouNovamente.set(true)
        }

        composeRule.onNodeWithText(
            "Não foi possível carregar os agendamentos. Tente novamente mais tarde."
        ).assertIsDisplayed()
        composeRule.onNodeWithText("Tentar novamente").performClick()
        assertTrue(tentouNovamente.get())
    }

    @Test
    fun successStateShowsAppointmentDetails() {
        mostrarEstado(UiState.Sucesso(listOf(agendamentoExemplo)))

        composeRule.onNodeWithText("Paciente: Ana").assertIsDisplayed()
        composeRule.onNodeWithText("Odontologia", substring = true).assertIsDisplayed()
    }

    private fun mostrarEstado(
        estado: UiState<List<Agendamento>>,
        aoTentarNovamente: () -> Unit = {}
    ) {
        composeRule.setContent {
            UBSTheme {
                NextAppointmentCard(estado, aoTentarNovamente)
            }
        }
    }

    private fun executarNoWorker(teste: suspend () -> Unit) {
        val task = FutureTask<Unit> { runBlocking { teste() } }
        Thread(task, "AgendamentoUiStateTest").start()
        task.get(15, TimeUnit.SECONDS)
    }

    private fun criarViewModel(
        fonte: () -> Flow<List<Agendamento>>
    ): AgendamentoViewModel {
        val dao = object : AgendamentoDao {
            override suspend fun inserir(agendamento: Agendamento): Long = 1L
            override fun listar(): Flow<List<Agendamento>> = fonte()
        }
        return AgendamentoViewModel(AgendamentoRepository(dao)).also {
            viewModelStore.put("agendamento", it)
        }
    }

    private suspend fun aguardar(
        viewModel: AgendamentoViewModel,
        predicate: (UiState<List<Agendamento>>) -> Boolean
    ): UiState<List<Agendamento>> = withTimeout(5_000) {
        viewModel.uiState.first(predicate)
    }

    private companion object {
        val agendamentoExemplo = Agendamento(
            id = 1,
            nome = "Ana",
            data = "04/11/2026",
            cartaoSUS = "123456789012345",
            horario = "09:30",
            servico = "Odontologia"
        )
    }
}

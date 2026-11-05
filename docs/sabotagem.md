# Testes de sabotagem dos estados da tela inicial

Os cenários abaixo foram executados na AVD `Medium_Phone_API_36.1` com os
testes instrumentados `AgendamentoUiStateTest`. Cada falha foi injetada por um
DAO falso no teste; nenhuma sabotagem foi mantida no código de produção.

| # | Sabotagem e interrupção controlada | Execução | Estado observado e mensagem | App permaneceu aberto? | Recuperação | Resultado |
|---|---|---|---|---|---|---|
| 1 | DAO retorna uma lista vazia, simulando ausência de agendamentos no banco. | `emptyDatabaseShowsEmptyState` e `emptyStateShowsHelpfulMessage` | `Vazio`; “Nenhum agendamento encontrado.” | Sim; os testes instrumentados concluíram sem crash. | Não era necessária. | Aprovado |
| 2 | Consulta ao Room lança `SQLiteException`, simulando falha de leitura do banco. | `databaseFailureShowsErrorAndKeepsViewModelAlive` e `errorStateShowsMessageAndRetryButton` | `Erro`; “Não foi possível carregar os agendamentos. Tente novamente mais tarde.” | Sim; estado de erro publicado sem encerrar o processo de teste. | Botão de nova tentativa exibido e callback verificado. | Aprovado |
| 3 | Primeira consulta falha; a fonte é recuperada antes de acionar nova tentativa. | `retryAfterDatabaseFailureLoadsAppointments` | Primeiro `Erro` com mensagem de falha; após tentar novamente, `Sucesso` com o agendamento. | Sim; sem crash. | Sim; `carregarAgendamentos()` refez a consulta e carregou o dado. | Aprovado |
| 4 | Consulta permanece pendente até a fonte concluir, simulando leitura lenta. | `delayedDatabaseQueryStaysLoadingUntilDataArrives` e `loadingStateShowsProgressMessage` | `Carregando`; “Carregando agendamentos...”; após a emissão, `Sucesso`. | Sim; sem crash. | Sim; a consulta concluiu e o estado foi atualizado. | Aprovado |
| 5 | Leitura em andamento é cancelada por uma nova tentativa, simulando interrupção e reinício da consulta. | `cancellingOldQueryDuringRetryDoesNotBecomeAnError` | Continua em `Carregando` durante a troca e chega a `Sucesso`; nenhuma mensagem de erro indevida. | Sim; sem crash. | Sim; a consulta substituta carregou os dados. | Aprovado |

O teste instrumentado adicional `successStateShowsAppointmentDetails` confirmou
que o estado `Sucesso` apresenta os dados reais do agendamento.

## Modo avião

O aplicativo foi aberto na AVD, o modo avião foi habilitado com
`cmd connectivity airplane-mode enable` e o processo do app e a
`MainActivity` permaneceram ativos. O estado anterior do modo avião era
desabilitado e foi restaurado ao final. Como os dados vêm do Room local, o
modo avião não interrompe essa consulta. A leitura automatizada da árvore
visual não foi possível: `uiautomator dump` retornou
`ERROR: null root node returned by UiTestAutomationBridge`; por isso não se
registra uma mensagem visual observada nesse teste manual. Os estados e textos
foram verificados nos testes instrumentados acima.

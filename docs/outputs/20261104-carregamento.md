# Relatório — Estado Carregando

## Implementação verificada

O branch `feature/estados-da-tela` já continha a hierarquia compartilhada
`UiState` e a integração do `AgendamentoViewModel` com a consulta ao Room. O
ViewModel inicia em `Carregando`, publica `Vazio` ou `Sucesso` quando a fonte
emite, publica `Erro` diante de falhas reais e relança
`CancellationException`. A operação `carregarAgendamentos()` refaz a consulta.
Esses componentes foram preservados, sem criar estado ou ViewModel duplicado.

A tela inicial já exibia indicador e mensagem enquanto carregava. Para
completar a verificação de que o indicador não persiste após o fim da
operação, foi adicionado a ele o identificador de teste
`home_loading_indicator` e um teste instrumentado que muda o estado de
`Carregando` para `Sucesso`, verifica que o indicador desaparece e confirma
que os dados são exibidos.

## Arquivos alterados nesta execução

- `src/app/src/main/java/br/edu/ifpe/ubsaude/Screens.kt`: marcador semântico para localizar o indicador de carregamento em testes.
- `src/app/src/androidTest/java/br/edu/ifpe/ubsaude/AgendamentoUiStateTest.kt`: cobertura explícita da presença e remoção do indicador na transição para sucesso.
- `docs/outputs/20261104-carregamento.md`: este relatório.

## Contrato compartilhado

- `UiState.kt`: `UiState.Carregando`, `UiState.Vazio`, `UiState.Erro(mensagemRecurso)` e `UiState.Sucesso(dados)`.
- `AgendamentoViewModel.uiState`: estado observável da lista.
- `AgendamentoViewModel.carregarAgendamentos()`: operação para tentar novamente.
- `Sucesso.dados`: lista de agendamentos entregue à interface.
- `Erro.mensagemRecurso`: ID de recurso para exibir uma mensagem localizada.

Os integrantes seguem o mesmo estado e o componente `NextAppointmentCard`,
sem implementações paralelas.

## Validação

Executado na AVD `Medium_Phone_API_36.1`:

- `testDebugUnitTest`: passou.
- `connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=br.edu.ifpe.ubsaude.AgendamentoUiStateTest`: **10 testes passaram, 0 falharam**. Inclui transições no ViewModel, apresentação de cada estado, ação de nova tentativa e desaparecimento do progresso após sucesso.
- `assembleDebug`: passou.
- Conferência de `strings.xml`: sem nomes duplicados ou IDs usados no Kotlin ausentes.

Uma tentativa de execução foi inicialmente impedida porque a AVD não estava
conectada; a AVD foi reiniciada e a execução final completa foi bem-sucedida.

## Branch e Git

- Branch atual: `feature/estados-da-tela`.
- O plano `docs/prompts/20260411-carregamento.md` foi preservado.
- Nenhum commit, push, merge ou Pull Request foi criado.

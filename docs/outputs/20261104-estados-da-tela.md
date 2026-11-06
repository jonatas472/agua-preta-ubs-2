# Relatório — Estados da tela inicial

## Resultado

Implementados e integrados os estados compartilhados `Carregando`, `Vazio`,
`Erro` e `Sucesso` para a consulta de agendamentos da tela inicial. A navegação
e as ações da home permanecem disponíveis enquanto o card de agendamentos
apresenta o estado correspondente.

## Arquivos

- `src/app/src/main/java/br/edu/ifpe/ubsaude/UiState.kt`: hierarquia selada dos estados.
- `src/app/src/main/java/br/edu/ifpe/ubsaude/AgendamentoViewModel.kt`: transições do Room, propagação de erros, cancelamento e nova tentativa.
- `src/app/src/main/java/br/edu/ifpe/ubsaude/MainActivity.kt`: observa e encaminha o estado e a ação de recarregar.
- `src/app/src/main/java/br/edu/ifpe/ubsaude/Screens.kt`: progresso, mensagem de vazio, erro com botão e conteúdo real no sucesso.
- `src/app/src/main/res/values/strings.xml`: mensagem e ação de nova tentativa.
- `src/app/src/androidTest/java/br/edu/ifpe/ubsaude/AgendamentoUiStateTest.kt`: cinco cenários de sabotagem e testes de apresentação dos quatro estados.
- `docs/sabotagem.md`: execução, resultados e limitações.

## Testes e build

Na AVD `Medium_Phone_API_36.1`, `connectedDebugAndroidTest` executou nove
testes: **9 passaram, 0 falharam**. Os cinco cenários controlados foram
complementados por quatro verificações Compose de apresentação das mensagens,
do botão de nova tentativa e dos detalhes do agendamento.

No teste manual em modo avião, o processo do app e a `MainActivity`
permaneceram ativos e o estado do modo avião foi restaurado. A leitura da
árvore de acessibilidade falhou no próprio `uiautomator` com
`null root node`; o texto visível foi, portanto, confirmado pelos testes
Compose, não por essa inspeção manual.

`testDebugUnitTest assembleDebug` terminou com `BUILD SUCCESSFUL`.

## Organização, branch e Git

- Branch de trabalho: `feature/estados-da-tela`, criada a partir do estado atual para preservar a integração existente.
- Não houve commits, push, merge ou abertura de Pull Request.
- A divisão de responsabilidades do planejamento foi tratada por componente; o Git não registra contribuições individuais dos nomes listados no plano.
- `docs/PRD.md` é identificado como rascunho histórico e aponta para um PRD vigente em `../PRD.md`, que não existe neste repositório. O documento histórico foi lido, sem criar um PRD novo.

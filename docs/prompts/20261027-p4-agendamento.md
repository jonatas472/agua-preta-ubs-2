# Plano — P4: implementação de Agendamento

## Contexto e objetivo

Implementar exclusivamente a parte de Agendamento da P4 do aplicativo Água Preta UBS, completando a persistência local com Room e conectando o formulário, a confirmação e a lista de agendamentos ao fluxo existente.

## Escopo

- Conferir a entidade `Agendamento` e manter os campos documentados: `id`, `nome`, `data`, `cartaoSUS`, `horario` e `servico`.
- Criar ou completar o DAO com inserção e listagem observável.
- Registrar a entidade e o DAO na única instância de `RoomDatabase`.
- Criar ou completar o ViewModel para validar campos obrigatórios, salvar o registro e expor resultados à interface.
- Conectar formulário, confirmação, tela de próximos agendamentos e estado vazio.
- Registrar a implementação e as verificações em `docs/outputs/20261027-p4-agendamento.md`.

## Arquivos previstos

- `src/app/src/main/java/br/edu/ifpe/ubsaude/AgendamentoDao.kt`
- `src/app/src/main/java/br/edu/ifpe/ubsaude/AgendamentoViewModel.kt`
- `src/app/src/main/java/br/edu/ifpe/ubsaude/AppDatabase.kt`
- `src/app/src/main/java/br/edu/ifpe/ubsaude/MainActivity.kt`
- `src/app/src/main/java/br/edu/ifpe/ubsaude/Screens.kt`
- `src/app/src/main/res/values/strings.xml`
- `docs/prompts/20261027-p4-agendamento.md`
- `docs/outputs/20261027-p4-agendamento.md`

## Restrições e critérios de conclusão

- Não modificar Cadastro, Serviço, Vacina ou funcionalidades de outros integrantes.
- Não criar Retrofit, endpoints, login, sincronização ou envio de dados pessoais.
- Não alterar dependências nem arquivos Gradle.
- Preservar a entidade existente e evitar duplicações.
- Migrar o banco apenas se a inclusão da tabela exigir a compatibilidade com bancos já criados.
- Validar os campos obrigatórios e comunicar sucesso e falha sem fechar o aplicativo.
- Executar as verificações disponíveis, revisar o diff e não criar commit nem push sem autorização.

## Divergência documental conhecida

O arquivo disponível `docs/PRD.md` está marcado como rascunho histórico e não representa o PRD vigente. A especificação do PRD vigente preservada no histórico do repositório define `id: Int` autogerado, compatível com a entidade já recuperada. O plano de execução cita uma definição anterior com `id: Long`; essa diferença deve permanecer registrada e não justifica alterar a entidade sem uma fonte vigente que confirme a mudança.
